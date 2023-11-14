package com.kkori.kkori.walk.entity;

import com.kkori.kkori.dog.entity.Dog;
import com.kkori.kkori.member.entity.Member;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.awt.datatransfer.FlavorEvent;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@DynamicUpdate
@DynamicInsert
public class Walk {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "walk_id")
    private Long walkId;

    @Column(name = "walk_start_time")
    private LocalDateTime walkStartTime;

    private int totalTime;

    @OneToMany(mappedBy = "walk", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WalkPath> walkPath = new ArrayList<>();

    @Column(name = "walk_distance", nullable = false, length = 5)
    private Double walkDistance;

    @Column(name = "calories", nullable = false, length = 5)
    private Double calories;

    private Long postId;

    @ManyToOne
    @JoinColumn(name = "member_id", referencedColumnName = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "sitter_id", referencedColumnName = "member_id")
    private Member sitter;


    public void setMemberAndSitter(Member member, Member sitter){
        this.member = member;
        this.sitter = sitter;
    }

    public void addWalkPath(List<WalkPath> walkPaths) {
        this.walkPath = walkPaths;
    }


}
