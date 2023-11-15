package com.kkori.kkori.jobboardlike.service;


import com.kkori.kkori.dog.dto.RegisterDogResponse;
import com.kkori.kkori.dog.entity.Dog;
import com.kkori.kkori.dogjobboard.entity.DogJobBoard;
import com.kkori.kkori.jobboard.dto.RegisterJobBoardResponse;
import com.kkori.kkori.jobboard.entity.JobBoard;
import com.kkori.kkori.jobboard.repository.JobBoardRepository;
import com.kkori.kkori.jobboardlike.entity.JobBoardLike;
import com.kkori.kkori.jobboardlike.repository.JobBoardLikeRepository;
import com.kkori.kkori.member.entity.Member;
import com.kkori.kkori.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class JobBoardLikeService {

    private final JobBoardRepository jobBoardRepository;

    private final MemberRepository memberRepository;

    private final JobBoardLikeRepository jobBoardLikeRepository;


    @Transactional
    public RegisterJobBoardResponse addLike(Long memberId, Long jobBoardId) {
        Member member = getMember(memberId);

        JobBoard jobBoard = getJobBoard(jobBoardId);
        
        if(jobBoardLikeRepository.findByMemberAndJobBoard(member,jobBoard).isPresent()) {
            throw new IllegalArgumentException("이미 찜한 게시물 입니다");
        }

        JobBoardLike build = JobBoardLike.builder()
                .jobBoard(jobBoard)
                .member(member)
                .build();

        jobBoardLikeRepository.save(build);

        return new RegisterJobBoardResponse(jobBoard);
    }
    
    @Transactional
    public void removeLike (Long memberId, Long jobBoardId){
        Member member = getMember(memberId);
        JobBoard jobBoard = getJobBoard(jobBoardId);

        JobBoardLike jobBoardLike = jobBoardLikeRepository.findByMemberAndJobBoard(member, jobBoard)
                .orElseThrow(() -> new IllegalArgumentException("내가 좋아요 누른 게시물이 아님"));

        jobBoardLikeRepository.delete(jobBoardLike);
    }

    public List<RegisterJobBoardResponse> findAllLikeJobBoard(Long memberId){
        Member member = getMember(memberId);

        List<JobBoardLike> byMember = jobBoardLikeRepository.findByMember(member);

        List<JobBoard> jobBoards = byMember.stream()
                .map(JobBoardLike::getJobBoard)
                .collect(Collectors.toList());


        List<RegisterJobBoardResponse> responses = jobBoards.stream()
                .map(jobBoard -> {
                    List<DogJobBoard> dogJobBoards = jobBoard.getDogJobBoards();

                    List<RegisterDogResponse> dogs = dogJobBoards.stream()
                            .map(DogJobBoard::getDog)
                            .map(RegisterDogResponse::new)
                            .collect(Collectors.toList());

                    return new RegisterJobBoardResponse(jobBoard, dogs);
                })
                .collect(Collectors.toList());

        return responses;

    }

    private JobBoard getJobBoard(Long jobBoardId) {
        return jobBoardRepository.findById(jobBoardId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시물"));
    }

    private Member getMember(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원"));
    }

}
