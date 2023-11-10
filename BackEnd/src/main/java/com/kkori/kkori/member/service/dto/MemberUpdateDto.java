package com.kkori.kkori.member.service.dto;

import com.kkori.kkori.member.entity.Member;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class MemberUpdateDto {
    private String nickName;
    private String introduce;


    public MemberUpdateDto(Member member) {
        this.nickName = member.getMemberInfo().getNickName();
        this.introduce = member.getMemberInfo().getIntroduce();
    }
}
