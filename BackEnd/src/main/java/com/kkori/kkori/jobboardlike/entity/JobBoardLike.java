package com.kkori.kkori.jobboardlike.entity;

import com.kkori.kkori.jobboard.entity.JobBoard;
import com.kkori.kkori.member.entity.Member;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Builder
@Getter
@DynamicInsert
@DynamicUpdate
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class JobBoardLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long jobBoardLikeId;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private JobBoard jobBoard ;


    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
}
