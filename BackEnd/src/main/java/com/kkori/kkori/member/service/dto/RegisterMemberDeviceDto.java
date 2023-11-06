package com.kkori.kkori.member.service.dto;

import com.kkori.kkori.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class RegisterMemberDeviceDto {

    private String deviceNumber;

    public RegisterMemberDeviceDto(Member member){
        this.deviceNumber = member.getDeviceNumber();
    }

}
