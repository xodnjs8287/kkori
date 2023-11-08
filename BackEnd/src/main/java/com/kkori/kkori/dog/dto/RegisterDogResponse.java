package com.kkori.kkori.dog.dto;


import com.kkori.kkori.dog.entity.Dog;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class RegisterDogResponse {

    private Long dogId;

    private String dogName;

    private LocalDate dogBirthDay;

    private String gender;

    private int dogAge;

    private String dogBreed;

    private BigDecimal dogWeight;

    private Boolean dogNeuter;

    private Boolean isLostDog;

    private Boolean isRegistered;

    private String imageUrl;


    public RegisterDogResponse(Dog dog) {
        if (dog == null) {
            throw new IllegalArgumentException("dog cannot be null");
        }
        this.dogId = dog.getDogId();
        this.dogName = Optional.ofNullable(dog.getDogName()).orElse(null);
        this.dogBreed = Optional.ofNullable(dog.getDogBreed()).orElse(null);
        this.dogBirthDay = Optional.ofNullable(dog.getDogBirthDay()).orElse(null);
        if (this.dogBirthDay != null) {
            LocalDate today = LocalDate.now();
            this.dogAge = Period.between(this.dogBirthDay, today).getYears();
        } else {
            this.dogAge = 0;
        }
        this.gender = Optional.ofNullable(dog.getGender())
                .map(gender -> Optional.ofNullable(gender.getEnGender()).orElse(null))
                .orElse(null);

        this.dogNeuter = Optional.ofNullable(dog.getDogNeuter()).orElse(false);
        this.dogWeight = Optional.ofNullable(dog.getDogWeight()).orElse(null);
        this.isRegistered = Optional.ofNullable(dog.getIsRegistered()).orElse(false);
        this.isLostDog = Optional.ofNullable(dog.getIsLostDog()).orElse(false);
        this.imageUrl = Optional.ofNullable(dog.getImage()).orElse(null);
    }

}
