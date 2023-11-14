package com.kkori.kkori.walk.dto;

import com.kkori.kkori.walk.entity.WalkPath;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class WalkPathRequest {

    private Double latitude;
    private Double latitudeDelta;
    private Double longitude;
    private Double longitudeDelta;

    public WalkPath toWalkPath(){
        return WalkPath.builder()
                .latitude(latitude)
                .latitudeDelta(latitudeDelta)
                .longitude(longitude)
                .longitudeDelta(longitudeDelta)
                .build();
    }
}
