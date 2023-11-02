package com.kkori.kkori.walk.service;

import com.kkori.kkori.walk.repository.WalkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WalkService {

    private final WalkRepository walkRepository;

}
