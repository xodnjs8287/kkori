package com.kkori.kkori.jobboard.service;

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

    @Transactional
    public RegisterJobBoardResponse register(Long id, RegisterJobBoardRequest request) {
        Member member = getMember(id);

        JobBoard jobBoard = request.toJobBoard();
        jobBoard.assignMember(member);

        LocationRequest locationRequest = locationService.callXY(request.getAddress());

        if (locationRequest != null) {
            LocationInfo locationInfo = locationRepository.findByLatitudeAndLongitude(
                    BigDecimal.valueOf(locationRequest.getLatitude()),
                    BigDecimal.valueOf(locationRequest.getLongitude())
            ).orElseGet(() ->
                    locationService.callApi(locationRequest));

            jobBoard.assignLocation(locationInfo);
        }

        RegisterJobBoardResponse registerJobBoardResponse = new RegisterJobBoardResponse(jobBoardRepository.save(jobBoard));

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
                    RegisterJobBoardResponse response = new RegisterJobBoardResponse(jobBoard);
                    Member member = jobBoard.getMember();
                    response.setEmail(member.getEmail());
                    response.setNickName(member.getMemberInfo().getNickName());
                    return response;
                })
                .collect(Collectors.toList());
    }






}
