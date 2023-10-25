package com.kkori.kkori.jobboard.service;

import com.kkori.kkori.jobboard.dto.RegisterJobBoardRequest;
import com.kkori.kkori.jobboard.dto.RegisterJobBoardResponse;
import com.kkori.kkori.jobboard.entity.JobBoard;
import com.kkori.kkori.jobboard.repository.JobBoardRepository;
import com.kkori.kkori.member.entity.Member;
import com.kkori.kkori.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JobBoardService {

    private final JobBoardRepository jobBoardRepository;

    private final MemberRepository memberRepository;


    public RegisterJobBoardResponse register(Long id, RegisterJobBoardRequest request) {

        Member member = memberRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 회원")
        );

        JobBoard jobBoard = request.toJobBoard();
        jobBoard.assignMember(member);

        return new RegisterJobBoardResponse(jobBoardRepository.save(jobBoard));
    }
}
