package com.kkori.kkori.location.sidoareas.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EqualsAndHashCode
@DynamicUpdate
@DynamicInsert
public class SidoArea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sidoId;

    private String sidoCode;

    private String sidoName;

    @OneToMany(mappedBy = "sidoArea", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<com.kkori.kkori.location.sidoareas.entity.SiggArea> siggAreas = new ArrayList<>();

}
