package com.kkori.kkori.jobboard.entity;

import com.kkori.kkori.baseEntity.BaseEntity;
import com.kkori.kkori.member.entity.Member;
import com.kkori.kkori.validation_field.Content;
import com.kkori.kkori.validation_field.Title;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@DynamicUpdate
@DynamicInsert
public class JobBoard extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long postId;

    @Embedded
    private Title title;

    @Embedded
    private Content content;

    private Integer payment;

    @ManyToOne
    @JoinColumn (name = "member_id")
    private Member member;


}
