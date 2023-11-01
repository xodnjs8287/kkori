package com.kkori.kkori.dog.repository;

import com.kkori.kkori.dog.entity.Dog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DogRepository extends JpaRepository<Dog,Long> {
}
