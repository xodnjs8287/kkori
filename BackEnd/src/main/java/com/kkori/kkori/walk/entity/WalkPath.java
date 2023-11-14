package com.kkori.kkori.walk.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WalkPath {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long walkPathId;
    private Double latitude;
    private Double latitudeDelta;
    private Double longitude;
    private Double longitudeDelta;

    @ManyToOne
    @JoinColumn(name = "walk_id")
    private Walk walk;

    public void setWalk(Walk walk){
        this.walk = walk;
    }

}
