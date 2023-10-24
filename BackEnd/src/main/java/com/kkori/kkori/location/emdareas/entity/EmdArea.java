package com.kkori.kkori.location.emdareas.entity;

import com.kkori.kkori.location.sidoareas.entity.SiggArea;
import com.kkori.kkori.location.userregion.entity.UserRegion;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EqualsAndHashCode
@DynamicUpdate
@DynamicInsert
public class EmdArea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long regionId;

    private String admCode;

    private String regionName;

    private String geom;

    private String locationXY;

    @ManyToOne
    private SiggArea siggArea;

    @OneToMany(mappedBy = "emdArea", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<UserRegion> userRegions;

}
