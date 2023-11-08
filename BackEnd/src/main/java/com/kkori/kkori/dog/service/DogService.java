package com.kkori.kkori.dog.service;


import com.kkori.kkori.dog.dto.RegisterDogRequest;
import com.kkori.kkori.dog.dto.RegisterDogResponse;
import com.kkori.kkori.dog.dto.UpdateDogRequest;
import com.kkori.kkori.dog.dto.UpdateDogResponse;
import com.kkori.kkori.dog.entity.Dog;
import com.kkori.kkori.dog.repository.DogRepository;
import com.kkori.kkori.dogimages.entity.DogImages;
import com.kkori.kkori.dogimages.repository.DogImagesRepository;
import com.kkori.kkori.member.entity.Member;
import com.kkori.kkori.member.repository.MemberRepository;
import com.kkori.kkori.s3.service.S3Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DogService {

    private final DogRepository dogRepository;

    private final S3Service s3Service;
    
    private final DogImagesRepository dogImagesRepository;

    private final MemberRepository memberRepository;

    @Transactional
    public RegisterDogResponse registerDog (Long memberId, RegisterDogRequest request){

        Member member = getMember(memberId);


        Dog dog = request.toDog();


        dog.setMember(member);

        Dog savedDog = dogRepository.save(dog);

        List<DogImages> savedImagesList = new ArrayList<>();

        for (MultipartFile image : request.getDogImages()){
            DogImages images = new DogImages(s3Service.uploadFile(image));
            
            images.setDog(savedDog);
            DogImages savedImage = dogImagesRepository.save(images);
            savedImagesList.add(savedImage);

        }
        
        dog.setImages(savedImagesList);

        Dog save = dogRepository.save(savedDog);

        return new RegisterDogResponse(save);
    }

    public RegisterDogResponse dogDetail (Long dogId){
        Dog dog = getDog(dogId);
        return new RegisterDogResponse(dog);
    }


    @Transactional
    public void deleteDog(Long memberId, Long dogId){

        getMember(memberId);

        Dog dog = getDog(dogId);

        dogRepository.delete(dog);


    }

    @Transactional
    public UpdateDogResponse updateDog(Long memberId, Long dogId, UpdateDogRequest request) {
        getMember(memberId);

        Dog dog = getDog(dogId);

        dog.updateDogInfo(
                request.getDogName(),
                request.getDogBirthDay(),
                request.getGender(),
                request.getDogBreed(),
                request.getDogWeight(),
                request.getDogNeuter(),
                request.getIsLostDog(),
                request.getIsRegistered()
        );

        Dog updatedDog = dogRepository.save(dog);

        return new UpdateDogResponse(updatedDog);
    }

    public List<RegisterDogResponse> findAllDogByMemberId(Long memberId){
        Member member = getMember(memberId);
        return dogRepository.findAllByMember(member)
                .stream()
                .map(RegisterDogResponse::new)
                .collect(Collectors.toList());
    }

    private Member getMember(Long id){
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
