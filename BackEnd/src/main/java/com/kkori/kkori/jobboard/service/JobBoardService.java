package com.kkori.kkori.jobboard.service;

import com.kkori.kkori.jobboard.repository.JobBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JobBoardService {

    private final JobBoardRepository jobBoardRepository;
}
