package com.kkori.kkori.jobboard.dto;

import com.kkori.kkori.dog.dto.RegisterDogResponse;
import com.kkori.kkori.dogjobboard.entity.DogJobBoard;
import com.kkori.kkori.jobboard.entity.JobBoard;
import com.kkori.kkori.location.dto.LocationResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateJobBoardResponse {

    private String title;

    private String content;

    private int payment;

    private LocationResponse locationResponse;

    private List<RegisterDogResponse> dogs = new ArrayList<>();

    public UpdateJobBoardResponse(JobBoard jobBoard){
        this.title = Optional.ofNullable(jobBoard.getTitleValue()).orElse(null);
        this.content = Optional.ofNullable(jobBoard.getContentValue()).orElse(null);
        this.payment = Optional.ofNullable(jobBoard.getPayment()).orElse(null);
        this.locationResponse = Optional.ofNullable(jobBoard.getLocationInfo()).map(LocationResponse::new).orElse(null);
        this.dogs = Optional.ofNullable(jobBoard.getDogJobBoards()
                .stream()
                .map(DogJobBoard::getDog)
                .map(RegisterDogResponse::new)
                .collect(Collectors.toList())).orElse(null);
    }
}
