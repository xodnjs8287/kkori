package com.kkori.kkori.walk.dto;

import com.kkori.kkori.walk.entity.WalkPath;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class WalkPathResponse {

    private Double latitude;
    private Double latitudeDelta;
    private Double longitude;
    private Double longitudeDelta;

    public WalkPathResponse(WalkPath walkPath){
        this.latitude = walkPath.getLatitude();
        this.latitudeDelta = walkPath.getLatitudeDelta();
        this.longitude = walkPath.getLongitude();
        this.longitudeDelta = walkPath.getLongitudeDelta();
    }
}
