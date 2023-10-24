package com.kkori.kkori.member.dto.req;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder
public class MemberUpdateRequest {
    private String nickName;
    private String introduce;

    public MemberUpdateRequest(String nickName, String introduce) {
        this.nickName = nickName;
        this.introduce = introduce;
    }
}
