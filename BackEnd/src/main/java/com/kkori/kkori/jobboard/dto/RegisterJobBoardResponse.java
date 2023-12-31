package com.kkori.kkori.jobboard.dto;

import com.kkori.kkori.dog.dto.RegisterDogResponse;
import com.kkori.kkori.dogjobboard.entity.DogJobBoard;
import com.kkori.kkori.jobboard.entity.JobBoard;
import com.kkori.kkori.location.dto.LocationResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    private LocalDateTime createdTime;

    private String profileImage;

    private List<RegisterDogResponse> dogs = new ArrayList<>();


    public RegisterJobBoardResponse(JobBoard jobBoard, List<RegisterDogResponse> dogs) {

        this.jobBoardId = jobBoard.getPostId();
        this.title = Optional.ofNullable(jobBoard.getTitleValue()).orElse(null);
        this.content = Optional.ofNullable(jobBoard.getContentValue()).orElse(null);
        this.payment = Optional.ofNullable(jobBoard.getPayment()).orElse(null);
        this.locationResponse = Optional.ofNullable(jobBoard.getLocationInfo()).map(LocationResponse::new).orElse(null);
        this.dogs = dogs;
        this.createdTime = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
        this.profileImage = Optional.ofNullable(jobBoard.getMember().getMemberInfo().getProfileImg()).orElse(null);

    }

    public RegisterJobBoardResponse(JobBoard jobBoard) {

        this.jobBoardId = jobBoard.getPostId();
        this.title = Optional.ofNullable(jobBoard.getTitleValue()).orElse(null);
        this.content = Optional.ofNullable(jobBoard.getContentValue()).orElse(null);
        this.payment = Optional.ofNullable(jobBoard.getPayment()).orElse(null);
        this.locationResponse = Optional.ofNullable(jobBoard.getLocationInfo()).map(LocationResponse::new).orElse(null);
        this.profileImage = Optional.ofNullable(jobBoard.getMember().getMemberInfo().getProfileImg()).orElse(null);
        this.createdTime = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
        this.dogs = Optional.ofNullable(jobBoard.getDogJobBoards()
                .stream()
                .map(DogJobBoard::getDog)
                .map(RegisterDogResponse::new)
                .collect(Collectors.toList())).orElse(null);
    }
}
