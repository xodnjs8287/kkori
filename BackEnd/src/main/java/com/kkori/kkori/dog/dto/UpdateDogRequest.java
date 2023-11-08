package com.kkori.kkori.dog.dto;

import com.kkori.kkori.dog.entity.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

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
    private List<MultipartFile> dogImages;
}
