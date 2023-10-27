package com.kkori.kkori.device.dto;

import com.kkori.kkori.device.entity.Gender;

import java.math.BigDecimal;
import java.time.LocalDate;

public class RegisterDogRequest {

    private String deviceNumber;

    private String qrCode;

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
