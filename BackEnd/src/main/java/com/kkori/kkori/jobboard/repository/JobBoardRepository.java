package com.kkori.kkori.jobboard.repository;

import com.kkori.kkori.jobboard.entity.JobBoard;
import com.kkori.kkori.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JobBoardRepository extends JpaRepository<JobBoard,Long> {
    List<JobBoard> findAllByMember(Member member);
}
