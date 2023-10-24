package com.kkori.kkori.location.siggareas.entity;

import com.kkori.kkori.location.emdareas.entity.EmdArea;
import com.kkori.kkori.location.sidoareas.entity.SidoArea;
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
public class SiggArea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long siggId;

    private String siggCode;

    private String siggName;

    @ManyToOne
    private SidoArea sidoArea;

    @OneToMany(mappedBy = "siggArea", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<EmdArea> emdAreas = new ArrayList<>();

}
