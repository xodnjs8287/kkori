package com.kkori.kkori.jobboard.dto;

import com.kkori.kkori.jobboard.entity.JobBoard;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterJobBoardResponse {

    private long jobBoardId;

    private String title;

    private String content;

    private int payment;

    public RegisterJobBoardResponse (JobBoard jobBoard) {

        this.jobBoardId = jobBoard.getPostId();
        this.title = jobBoard.getTitleValue();
        this.content = jobBoard.getContentValue();
        this.payment = jobBoard.getPayment();

    }
}
