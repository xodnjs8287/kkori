package com.kkori.kkori.dog.service;


import com.kkori.kkori.dog.dto.RegisterDogRequest;
import com.kkori.kkori.dog.dto.RegisterDogResponse;
import com.kkori.kkori.dog.entity.Dog;
import com.kkori.kkori.dog.repository.DogRepository;
import com.kkori.kkori.member.entity.Member;
import com.kkori.kkori.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DogService {

    private final DogRepository dogRepository;

    private final MemberRepository memberRepository;

    @Transactional
    public RegisterDogResponse registerDog (Long memberId, RegisterDogRequest request){

        Member member = getMember(memberId);

        Dog dog = request.toDog();
        dog.setMember(member);

        Dog saved = dogRepository.save(dog);

        return new RegisterDogResponse(saved);
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

}
