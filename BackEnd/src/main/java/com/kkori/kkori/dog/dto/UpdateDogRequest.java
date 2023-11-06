package com.kkori.kkori.dog.dto;

import com.kkori.kkori.dog.entity.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateDogRequest {

    private String dogName;
    private LocalDate dogBirthDay;
    private Gender gender;
    private String dogBreed;
    private BigDecimal dogWeight;
    private Boolean dogNeuter;
    private Boolean isLostDog;
    private Boolean isRegistered;
    private String dogImages;
}