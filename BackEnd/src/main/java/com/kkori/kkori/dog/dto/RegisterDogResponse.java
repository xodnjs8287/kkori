package com.kkori.kkori.dog.dto;


import com.kkori.kkori.dog.entity.Dog;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class RegisterDogResponse {

    private String dogName;

    private LocalDate dogBirthDay;

    private String gender;

    private String dogBreed;

    private BigDecimal dogWeight;

    private Boolean dogNeuter;

    private Boolean isLostDog;

    private Boolean isRegistered;

    private String dogImages;

    public RegisterDogResponse (Dog device){
        this.dogName = device.getDogName();
        this.dogBreed = device.getDogBreed();
        this.gender = device.getGender().getEnGender();
        this.dogImages = device.getDogImages();
        this.dogNeuter = device.getDogNeuter();
        this.dogWeight = device.getDogWeight();
        this.isRegistered = device.getIsRegistered();
        this.isLostDog = device.getIsLostDog();

    }
}
