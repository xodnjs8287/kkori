package com.kkori.kkori.reservedhistory.dto;

import com.kkori.kkori.jobboard.entity.JobBoard;
import com.kkori.kkori.member.entity.Member;
import com.kkori.kkori.reservedhistory.entity.ReservedHistory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;


@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReservedHistoryResponse {

    private Long reservedHistoryId;

    private Long memberId;

    private String sitterEmail;

    private Long jobBoardId;

    private Boolean isCompleted;

    public ReservedHistoryResponse(ReservedHistory reservedHistory){
        this.isCompleted =reservedHistory.getIsCompleted();
        this.memberId = reservedHistory.getMember().getId();
        this.sitterEmail = reservedHistory.getSitter().getEmail();
        this.jobBoardId = reservedHistory.getJobBoard().getPostId();
        this.reservedHistoryId =reservedHistory.getReservedHistoryId();
    }


}
