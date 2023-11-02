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

@Service
@RequiredArgsConstructor
public class DogService {

    private final DogRepository dogRepository;

    private final MemberRepository memberRepository;

    @Transactional
    public RegisterDogResponse registerDog (Long memberId, RegisterDogRequest request){

        Member member = getMember(memberId);

        Dog device = request.toDevice();
        device.setMember(member);

        Dog saved = dogRepository.save(device);

        return new RegisterDogResponse(saved);
    }

    private Member getMember(Long id){
          return memberRepository.findById(id)
                .orElseThrow(()
                        -> new IllegalArgumentException("존재하지 않는 회원")
                );

    }

}
