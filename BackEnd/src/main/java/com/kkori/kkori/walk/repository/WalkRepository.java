package com.kkori.kkori.walk.repository;

import com.kkori.kkori.walk.entity.Walk;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WalkRepository extends JpaRepository<Walk,Long> {

    Optional<Walk> findByPostId(Long PostId);

    List<Walk> findAllByPostId(Long postId);
}
