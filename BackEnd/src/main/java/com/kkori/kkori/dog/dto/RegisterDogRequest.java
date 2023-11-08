package com.kkori.kkori.dog.dto;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.kkori.kkori.dog.entity.Dog;
import com.kkori.kkori.dog.entity.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Positive;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDogRequest {

    private String dogName;

    private LocalDate dogBirthDay;

    private Gender gender;

    private String dogBreed;
    
    @Positive
    private BigDecimal dogWeight;

    private Boolean dogNeuter;

    private MultipartFile image;

    public Dog toDog(){
        return Dog.builder()
                .dogName(this.dogName)
                .dogBirthDay(this.dogBirthDay)
                .gender(this.gender)
                .dogBreed(this.dogBreed)
                .dogWeight(this.dogWeight)
                .dogNeuter(this.dogNeuter)
                .build();

    }


}
