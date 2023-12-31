package com.kkori.kkori.dog.entity;

import com.kkori.kkori.baseEntity.BaseEntity;
import com.kkori.kkori.member.entity.Member;
import com.kkori.kkori.dogjobboard.entity.DogJobBoard;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Getter
@Setter
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

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;


    private String image;

    @OneToMany(mappedBy = "dog")
    private List<DogJobBoard> dogJobBoards = new ArrayList<>();

    public void setMember (Member member){
        this.member = member;
    }

    public void updateDogInfo(String dogName, LocalDate dogBirthDay, Gender gender, String dogBreed,
                              BigDecimal dogWeight, Boolean dogNeuter, Boolean isLostDog,
                              Boolean isRegistered,String image) {

        this.dogName = Optional.ofNullable(dogName).orElse(this.dogName);
        this.dogBirthDay = Optional.ofNullable(dogBirthDay).orElse(this.dogBirthDay);
        this.gender = Optional.ofNullable(gender).orElse(this.gender);
        this.dogBreed = Optional.ofNullable(dogBreed).orElse(this.dogBreed);
        this.dogWeight = Optional.ofNullable(dogWeight).orElse(this.dogWeight);
        this.dogNeuter = Optional.ofNullable(dogNeuter).orElse(this.dogNeuter);
        this.isLostDog = Optional.ofNullable(isLostDog).orElse(this.isLostDog);
        this.isRegistered = Optional.ofNullable(isRegistered).orElse(this.isRegistered);
        this.image = Optional.ofNullable(image).orElse(this.image);
    }
}
