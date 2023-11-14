package com.kkori.kkori.walk.service;

import com.kkori.kkori.jobboard.entity.JobBoard;
import com.kkori.kkori.jobboard.repository.JobBoardRepository;
import com.kkori.kkori.member.entity.Member;
import com.kkori.kkori.member.repository.MemberRepository;
import com.kkori.kkori.walk.dto.WalkPathRequest;
import com.kkori.kkori.walk.dto.WalkPathResponse;
import com.kkori.kkori.walk.dto.WalkRequest;
import com.kkori.kkori.walk.dto.WalkResponse;
import com.kkori.kkori.walk.entity.Walk;
import com.kkori.kkori.walk.entity.WalkPath;
import com.kkori.kkori.walk.repository.WalkPathRepository;
import com.kkori.kkori.walk.repository.WalkRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class WalkService {

    private final WalkRepository walkRepository;

    private final JobBoardRepository jobBoardRepository;

    private final MemberRepository memberRepository;

    private final WalkPathRepository walkPathRepository;

    @Transactional
    public WalkResponse registerWalk(Long sitterId, WalkRequest walkRequest) {

        Member member = memberRepository.findById(walkRequest.getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 멤버"));


        Member sitter = memberRepository.findById(sitterId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 멤버"));

        JobBoard jobBoard = jobBoardRepository.findById(walkRequest.getPostId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시물"));


        Walk walk = walkRequest.toWalk();

        Walk saved = walkRepository.save(walk);


        saved.setMemberAndSitter(member, sitter);


        List<WalkPathRequest> walkPath = walkRequest.getWalkPath();

        for (WalkPathRequest walkPathRequest : walkPath) {
            WalkPath walkPath1 = walkPathRequest.toWalkPath();
            walkPath1.setWalk(walk);
            walkPathRepository.save(walkPath1);
        }

        Walk savedWalk = walkRepository.save(saved);


        WalkResponse walkResponse = new WalkResponse(savedWalk);

        walkResponse.setWalkPath(getWalkPathResponses(savedWalk));

        walkResponse.setPostTitle(jobBoard.getTitleValue());


        return walkResponse;
    }

    private List<WalkPathResponse> getWalkPathResponses(Walk savedWalk) {
        return walkPathRepository.findAllByWalk(savedWalk).stream()
                .map(WalkPathResponse::new)
                .collect(Collectors.toList());
    }

}
