package com.kkori.kkori.jobboard.service;

import com.kkori.kkori.dog.dto.DogResponse;
import com.kkori.kkori.dog.dto.RegisterDogResponse;
import com.kkori.kkori.dog.entity.Dog;
import com.kkori.kkori.dog.repository.DogRepository;
import com.kkori.kkori.dogjobboard.entity.DogJobBoard;
import com.kkori.kkori.jobboard.dto.RegisterJobBoardRequest;
import com.kkori.kkori.jobboard.dto.RegisterJobBoardResponse;
import com.kkori.kkori.jobboard.entity.JobBoard;
import com.kkori.kkori.jobboard.repository.JobBoardRepository;
import com.kkori.kkori.location.dto.LocationRequest;
import com.kkori.kkori.location.entity.LocationInfo;
import com.kkori.kkori.location.repository.LocationRepository;
import com.kkori.kkori.location.service.LocationService;
import com.kkori.kkori.member.entity.Member;
import com.kkori.kkori.member.repository.MemberRepository;
import com.kkori.kkori.dogjobboard.repository.DogJobBoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class JobBoardService {

    private final JobBoardRepository jobBoardRepository;
    private final MemberRepository memberRepository;
    private final LocationService locationService;
    private final LocationRepository locationRepository;
    private final DogJobBoardRepository dogJobBoardRepository;
    private final DogRepository dogRepository;

    @Transactional
    public RegisterJobBoardResponse register(Long id, RegisterJobBoardRequest request) {
        Member member = getMember(id);

        List<Dog> selectedDogs = dogRepository.findAllByDogIdInAndMemberId(request.getDogIds(), id);

        if (selectedDogs.isEmpty()) {
            throw new IllegalArgumentException("선택된 강아지가 없습니다.");
        }

        JobBoard jobBoard = request.toJobBoard();
        jobBoard.assignMember(member);

        LocationRequest locationRequest = locationService.callXY(request.getAddress());

        if (locationRequest != null) {
            LocationInfo locationInfo = locationRepository.findTopByLatitudeAndLongitude(
                    BigDecimal.valueOf(locationRequest.getLatitude()),
                    BigDecimal.valueOf(locationRequest.getLongitude())
            ).orElseGet(() ->
                    locationService.callApi(locationRequest));

            jobBoard.assignLocation(locationInfo);
        }


        JobBoard savedJobBoard  = jobBoardRepository.save(jobBoard);

        for (Dog dog : selectedDogs) {

            DogJobBoard build = DogJobBoard.builder()
                    .dog(dog)
                    .jobBoard(savedJobBoard)
                    .build();
            dogJobBoardRepository.save(build);
        }



        List<RegisterDogResponse> dogResponses = selectedDogs.stream()
                .map(dog -> {
                    Dog save = dogRepository.findById(dog.getDogId()).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 강아지"));
                    return new RegisterDogResponse(save);
                })
                .collect(Collectors.toList());

        RegisterJobBoardResponse registerJobBoardResponse = new RegisterJobBoardResponse(savedJobBoard,dogResponses);

        registerJobBoardResponse.setEmail(member.getEmail());
        registerJobBoardResponse.setNickName(member.getMemberInfo().getNickName());


        return registerJobBoardResponse;
    }

    private Member getMember(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 회원")
        );
        return member;
    }

    public List<RegisterJobBoardResponse> findAll(){
        return jobBoardRepository.findAll()
                .stream()
                .map(jobBoard -> {
                    List<RegisterDogResponse> dogs = dogJobBoardRepository.findAllByJobBoard(jobBoard)
                            .stream()
                            .map(DogJobBoard::getDog)
                            .map(RegisterDogResponse::new)
                            .collect(Collectors.toList());

                    Member member = jobBoard.getMember();
                    RegisterJobBoardResponse response = new RegisterJobBoardResponse(
                            jobBoard,
                            dogs
                    );
                    response.setEmail(member.getEmail());
                    response.setNickName(member.getMemberInfo().getNickName());
                    response.setDogs(dogs);
                    return response;
                })
                .collect(Collectors.toList());
    }





}
