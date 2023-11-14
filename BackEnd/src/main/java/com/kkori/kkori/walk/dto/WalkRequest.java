package com.kkori.kkori.walk.dto;


import com.kkori.kkori.walk.entity.Walk;
import com.kkori.kkori.walk.entity.WalkPath;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class WalkRequest {

    private LocalDateTime walkStartTime;
    private int totalTime;
    private List<WalkPathRequest> walkPath = new ArrayList<>();
    private Double walkDistance;
    private Double calories;
    private Long postId;
    private Long memberId;

    public Walk toWalk() {
        Walk walk = Walk.builder()
                .walkStartTime(this.walkStartTime)
                .totalTime(this.totalTime)
                .walkDistance(this.walkDistance)
                .calories(this.calories)
                .postId(this.postId)
                .build();

        return walk;

    }
}
