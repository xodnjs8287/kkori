package com.kkori.kkori.dog.repository;

import com.kkori.kkori.dog.entity.Dog;
import com.kkori.kkori.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DogRepository extends JpaRepository<Dog,Long> {
    List<Dog> findAllByMember(Member member);

    List<Dog> findAllByIsLostDogIsTrue();

    List<Dog> findAllByMemberId(Long memberId);

    List<Dog> findAllByMemberIdAndIsLostDogIsTrue(Long memberId);

    List<Dog> findAllByMemberIdAndIsLostDogIsFalse(Long memberId);

    List<Dog> findAllByDogIdInAndMemberId(List<Long> dogIds, Long memberId);
}
