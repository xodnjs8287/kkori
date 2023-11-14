package com.kkori.kkori.walk.repository;

import com.kkori.kkori.walk.entity.Walk;
import com.kkori.kkori.walk.entity.WalkPath;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WalkPathRepository extends JpaRepository<WalkPath,Long> {

    List<WalkPath> findAllByWalk(Walk walk);
}
