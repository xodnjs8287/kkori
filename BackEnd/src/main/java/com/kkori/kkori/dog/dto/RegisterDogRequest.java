package com.kkori.kkori.dog.dto;

import com.kkori.kkori.dog.entity.Dog;
import com.kkori.kkori.dog.entity.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDogRequest {

    private String dogName;

    private LocalDate dogBirthDay;

    private Gender gender;

    private String dogBreed;

    private BigDecimal dogWeight;

    private Boolean dogNeuter;

    private String dogImages;

    public Dog toDog(){
        return Dog.builder()
                .dogName(this.dogName)
                .dogBirthDay(this.dogBirthDay)
                .gender(this.gender)
                .dogBreed(this.dogBreed)
                .dogWeight(this.dogWeight)
                .dogNeuter(this.dogNeuter)
                .dogImages(dogImages)
                .build();

    }
}
