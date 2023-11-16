package com.kkori.kkori.jobboard.dto;

import com.kkori.kkori.jobboard.entity.JobBoard;
import com.kkori.kkori.location.dto.LocationRequest;
import com.kkori.kkori.validation_field.Content;
import com.kkori.kkori.validation_field.Title;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateJobBoardRequest {

    private String title;

    private String content;

    private Integer payment;

    private String address;

    private List<Long> dogIds;

}
