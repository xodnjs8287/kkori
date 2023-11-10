package com.kkori.kkori.dogjobboard.repository;

import com.kkori.kkori.dogjobboard.entity.DogJobBoard;
import com.kkori.kkori.jobboard.entity.JobBoard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DogJobBoardRepository extends JpaRepository <DogJobBoard,Long> {
    List<DogJobBoard> findAllByJobBoard(JobBoard jobBoard);

}
