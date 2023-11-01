package com.kkori.kkori.walk.dto;


import com.kkori.kkori.dog.entity.Dog;
import com.kkori.kkori.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class WalkRequest {


    private LocalDateTime walkStartTime;

    private LocalDateTime walkEndTime;

    private String walkPath;

    private String walkDistance;

    private String calories;

    private Member member;

    private Dog device;

    //                .transactionDate(ZonedDateTime.now(ZoneId.of("Asia/Seoul")).toLocalDateTime())
}
