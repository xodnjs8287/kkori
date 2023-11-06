package com.kkori.kkori.dog.dto;

import com.kkori.kkori.dog.entity.Dog;
import com.kkori.kkori.dog.entity.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateDogResponse {

    private String dogName;
    private LocalDate dogBirthDay;
    private Gender gender;
    private String dogBreed;
    private BigDecimal dogWeight;
    private Boolean dogNeuter;
    private Boolean isLostDog;
    private Boolean isRegistered;
    private String dogImages;

    public UpdateDogResponse(Dog dog) {
        this.dogName = Optional.ofNullable(dog.getDogName()).orElse("Unknown");
        this.dogBirthDay = Optional.ofNullable(dog.getDogBirthDay()).orElse(LocalDate.now());
        this.gender = Optional.ofNullable(dog.getGender()).orElse(null);
        this.dogBreed = Optional.ofNullable(dog.getDogBreed()).orElse("Unknown Breed");
        this.dogWeight = Optional.ofNullable(dog.getDogWeight()).orElse(BigDecimal.ZERO);
        this.dogNeuter = Optional.ofNullable(dog.getDogNeuter()).orElse(false);
        this.isLostDog = Optional.ofNullable(dog.getIsLostDog()).orElse(false);
        this.isRegistered = Optional.ofNullable(dog.getIsRegistered()).orElse(false);
        this.dogImages = Optional.ofNullable(dog.getDogImages()).orElse("No Image Available");
    }
}
