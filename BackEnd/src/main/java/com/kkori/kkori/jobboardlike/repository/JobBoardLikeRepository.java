package com.kkori.kkori.jobboardlike.repository;

import com.kkori.kkori.jobboard.entity.JobBoard;
import com.kkori.kkori.jobboardlike.entity.JobBoardLike;
import com.kkori.kkori.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JobBoardLikeRepository extends JpaRepository<JobBoardLike,Long> {

    Optional<JobBoardLike> findByMemberAndJobBoard(Member member, JobBoard jobBoard);

    List<JobBoardLike> findByMember(Member member);

}
