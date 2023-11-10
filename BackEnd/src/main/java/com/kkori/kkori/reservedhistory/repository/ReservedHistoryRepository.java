package com.kkori.kkori.reservedhistory.repository;

import com.kkori.kkori.member.entity.Member;
import com.kkori.kkori.reservedhistory.entity.ReservedHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservedHistoryRepository extends JpaRepository<ReservedHistory,Long> {
    List<ReservedHistory> findAllByMember (Member member);

    List<ReservedHistory> findAllByMemberAndSitter(Member member, Member sitter);


}
