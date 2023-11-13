package com.kkori.kkori.jobboardlike.dto;

import com.kkori.kkori.jobboardlike.entity.JobBoardLike;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class JobBoardLikeResponse {

    private Long jobBoardId;

    private String memberEmail;

    public JobBoardLikeResponse(JobBoardLike jobBoardLike){
        this.jobBoardId = jobBoardLike.getJobBoard().getPostId();
        this.memberEmail = jobBoardLike.getMember().getEmail();
    }
}
