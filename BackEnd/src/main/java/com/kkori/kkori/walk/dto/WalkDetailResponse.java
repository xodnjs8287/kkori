package com.kkori.kkori.walk.dto;

import com.kkori.kkori.member.entity.Member;
import com.kkori.kkori.walk.entity.Walk;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WalkDetailResponse {

    private Long walkId;
    private LocalDateTime walkStartTime;
    private int totalTime;
    private List<WalkPathResponse> walkPath;
    private Double walkDistance;
    private Double calories;
    private Long postId;
    private String postTitle;
    private List<String> dogName;
    private List<String> dogImage;
    private Long memberId;
    private Long sitterId;

    public WalkDetailResponse(Walk walk){
        this.walkId = walk.getWalkId();
        this.walkStartTime = walk.getWalkStartTime();
        this.totalTime = walk.getTotalTime();
        this.walkPath = Optional.ofNullable(walk.getWalkPath())
                .orElseGet(Collections::emptyList)
                .stream()
                .map(WalkPathResponse::new)
                .collect(Collectors.toList());
        this.walkDistance = Optional.ofNullable(walk.getWalkDistance()).orElse(0.0);
        this.calories = Optional.ofNullable(walk.getCalories()).orElse(0.0);
        this.postId = Optional.ofNullable(walk.getPostId()).orElse(null);
        this.memberId = Optional.ofNullable(walk.getMember()).map(Member::getId).orElse(null);
        this.sitterId = Optional.ofNullable(walk.getSitter()).map(Member::getId).orElse(null);
    }
}
