package com.kkori.kkori.dog.service;


import com.kkori.kkori.dog.dto.*;
import com.kkori.kkori.dog.entity.Dog;
import com.kkori.kkori.dog.repository.DogRepository;
import com.kkori.kkori.member.entity.Member;
import com.kkori.kkori.member.repository.MemberRepository;
import com.kkori.kkori.s3.service.S3Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class DogService {

    private final DogRepository dogRepository;

    private final S3Service s3Service;

    private final MemberRepository memberRepository;

    @Transactional
    public RegisterDogResponse registerDog(Long memberId, RegisterDogRequest request) {

        Member member = getMember(memberId);

        Dog dog = request.toDog();

        String imageUrl = s3Service.uploadFile(request.getImage());

        dog.setImage(imageUrl);
        dog.setMember(member);

        Dog savedDog = dogRepository.save(dog);


        return new RegisterDogResponse(savedDog);
    }

    public RegisterDogResponse dogDetail(Long dogId) {
        Dog dog = getDog(dogId);
        return new RegisterDogResponse(dog);
    }

    public List<RegisterDogResponse> findAllLostDogs(){
        return dogRepository.findAllByIsLostDogIsTrue()
                .stream()
                .map(RegisterDogResponse::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public LostDogDto registerLostDog(Long memberId, Long dogId) {
        getMember(memberId);

        Dog dog = getDog(dogId);
        dog.setIsLostDog(true);

        Dog save = dogRepository.save(dog);

        return new LostDogDto(save);
    }

    @Transactional
    public void deleteDog(Long memberId, Long dogId) {

        getMember(memberId);

        Dog dog = getDog(dogId);

        dogRepository.delete(dog);


    }

    @Transactional
    public UpdateDogResponse updateDog(Long memberId, Long dogId, UpdateDogRequest request) {
        getMember(memberId);

        Dog dog = getDog(dogId);

        String updatedImage = s3Service.uploadFile(request.getImage());



        dog.updateDogInfo(
                request.getDogName(),
                request.getDogBirthDay(),
                request.getGender(),
                request.getDogBreed(),
                request.getDogWeight(),
                request.getDogNeuter(),
                request.getIsLostDog(),
                request.getIsRegistered(),
                updatedImage
        );

        Dog updatedDog = dogRepository.save(dog);

        log.info(updatedDog.getImage());

        return new UpdateDogResponse(updatedDog);
    }

    public List<RegisterDogResponse> findAllDogByMemberId(Long memberId) {
        Member member = getMember(memberId);
        return dogRepository.findAllByMember(member)
                .stream()
                .map(RegisterDogResponse::new)
                .collect(Collectors.toList());
    }

    private Member getMember(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(()
                        -> new IllegalArgumentException("존재하지 않는 회원")
                );

    }


    private Dog getDog(Long dogId) {
        Dog dog = dogRepository.findById(dogId).orElseThrow(()
                -> new IllegalArgumentException("존재하지 않는 강아지"));
        return dog;
    }


}
