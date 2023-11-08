package com.kkori.kkori.dogimages.entity;

import com.kkori.kkori.dog.entity.Dog;
import lombok.*;
import net.minidev.json.annotate.JsonIgnore;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
@DynamicInsert
public class DogImages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dogImageId;

    private String imageUrl;

    @ManyToOne
    @JsonIgnore
    private Dog dog;

    public DogImages(String dogImageUrl){
        this.imageUrl = dogImageUrl;
    }

    public void setDog(Dog dog){
        this.dog = dog;
    }



}
