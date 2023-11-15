package com.kkori.kkori.reservedhistory.repository;

import com.kkori.kkori.member.entity.Member;
import com.kkori.kkori.reservedhistory.entity.ReservedHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReservedHistoryRepository extends JpaRepository<ReservedHistory,Long> {
    List<ReservedHistory> findAllByMember (Member member);

    List<ReservedHistory> findAllBySitter (Member sitter);

    Optional<ReservedHistory> findByJobBoard_PostId (Long postId);
    List<ReservedHistory> findAllByMemberAndSitter(Member member, Member sitter);


}
