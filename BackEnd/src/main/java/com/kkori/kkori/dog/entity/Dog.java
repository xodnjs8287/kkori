package com.kkori.kkori.dog.entity;

import com.kkori.kkori.baseEntity.BaseEntity;
import com.kkori.kkori.member.entity.Member;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@DynamicUpdate
@DynamicInsert
public class Dog extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "dog_id")
    private Long dogId;

    private String dogName;

    private LocalDate dogBirthDay;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String dogBreed;

    private BigDecimal dogWeight;

    private Boolean dogNeuter;

    private Boolean isLostDog;

    private Boolean isRegistered;

    private String dogImages;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    public void setMember (Member member){
        this.member = member;
    }

}
