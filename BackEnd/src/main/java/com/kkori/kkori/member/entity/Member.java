package com.kkori.kkori.member.entity;

import com.kkori.kkori.baseEntity.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
@DynamicInsert
@ToString
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String code;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String birthday;
    @Column(nullable = false)
    private String gender;
    @Embedded
    @Column(name="oauth_provider") // Eebedded 가아님 enumerate 임 -> 테이블에 적용 안된 상태 / 그치만 현재 필요 x
    private OAuthProvider oAuthProvider;
    @Column(nullable = true, name = "refresh_token") // 초기에는 없음
    private String refreshToken;
    @Embedded
    private MemberInfo memberInfo;

    public void reJoinMember(MemberInfo memberInfo){
        this.memberInfo = memberInfo;
    }
    // 비즈니스 로직
    public void updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public void deleteRefreshToken() {
        this.refreshToken = null;
    }
}
