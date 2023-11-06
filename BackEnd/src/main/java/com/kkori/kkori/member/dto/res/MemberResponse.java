package com.kkori.kkori.member.dto.res;

import com.kkori.kkori.member.entity.Member;
import lombok.*;

import java.util.Objects;
import java.util.Optional;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberResponse {
    private String nickName;
    private String introduce;
    private String profileImg;
    private String email;
    private String name;
    private String deviceNumber;
    private Boolean isMyPage;

    public MemberResponse (Member member){
        this.nickName = member.getMemberInfo().getNickName();
        this.introduce = member.getMemberInfo().getIntroduce();
        this.email = member.getEmail();
        this.name = member.getName();
        this.deviceNumber = Optional.ofNullable(member.getDeviceNumber()).orElse(null);
        this.profileImg = Optional.ofNullable(member.getMemberInfo().getProfileImg()).orElse(null);
    }


}
