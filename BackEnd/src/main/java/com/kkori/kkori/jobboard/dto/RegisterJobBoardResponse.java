package com.kkori.kkori.jobboard.dto;

import com.kkori.kkori.jobboard.entity.JobBoard;
import com.kkori.kkori.location.dto.LocationResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterJobBoardResponse {

    private long jobBoardId;

    private String title;

    private String content;

    private int payment;

    private String email;

    private String nickName;

    private LocationResponse locationResponse;

    public RegisterJobBoardResponse (JobBoard jobBoard) {

        this.jobBoardId = jobBoard.getPostId();
        this.title = Optional.ofNullable(jobBoard.getTitleValue()).orElse(null);
        this.content = Optional.ofNullable(jobBoard.getContentValue()).orElse(null);
        this.payment = Optional.ofNullable(jobBoard.getPayment()).orElse(null);
        this.locationResponse = Optional.ofNullable(jobBoard.getLocationInfo()).map(LocationResponse::new).orElse(null);

    }
}
