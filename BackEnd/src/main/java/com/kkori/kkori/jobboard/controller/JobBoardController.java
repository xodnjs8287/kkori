package com.kkori.kkori.jobboard.controller;

import com.kkori.kkori.jobboard.dto.RegisterJobBoardRequest;
import com.kkori.kkori.jobboard.dto.RegisterJobBoardResponse;
import com.kkori.kkori.jobboard.service.JobBoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api")
public class JobBoardController {

    private final JobBoardService jobBoardService;

    @PostMapping("/post-job")
    public ResponseEntity<RegisterJobBoardResponse> register(
            final Authentication authentication,
            @RequestBody RegisterJobBoardRequest request) {
        Long name = Long.parseLong(authentication.getName());
        return ResponseEntity.ok(jobBoardService.register(name, request));
    }

    @GetMapping("/post/all")
    public ResponseEntity<List<RegisterJobBoardResponse>> findAll(){
        return ResponseEntity.ok(jobBoardService.findAll());
    }
}
