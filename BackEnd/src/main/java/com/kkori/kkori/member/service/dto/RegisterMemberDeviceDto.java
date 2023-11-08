package com.kkori.kkori.member.service.dto;

import com.kkori.kkori.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class RegisterMemberDeviceDto {

    @Pattern(regexp = "\\d{6}", message = "deviceNumber must be a 6-digit number")
    private String deviceNumber;

    public RegisterMemberDeviceDto(Member member){
        this.deviceNumber = member.getDeviceNumber();
    }

}
