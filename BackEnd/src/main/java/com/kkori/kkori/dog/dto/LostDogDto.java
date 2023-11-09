package com.kkori.kkori.dog.dto;


import com.kkori.kkori.dog.entity.Dog;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LostDogDto {

    private boolean isLostDog;

    public LostDogDto (Dog dog){
        this.isLostDog = dog.getIsLostDog();
    }
}
