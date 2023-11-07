package com.kkori.kkori.jobboard.controller;

import com.kkori.kkori.jobboard.dto.RegisterJobBoardRequest;
import com.kkori.kkori.jobboard.dto.RegisterJobBoardResponse;
import com.kkori.kkori.jobboard.service.JobBoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api")
public class JobBoardController {

    private final JobBoardService jobBoardService;

    @PostMapping(value = "/post-job", consumes = {"multipart/form-data", "application/json"})
    public ResponseEntity<RegisterJobBoardResponse> register(
            final Authentication authentication,
            @ModelAttribute RegisterJobBoardRequest request,
            @RequestParam(required = false) MultipartFile file) {
        Long name = Long.parseLong(authentication.getName());
        return ResponseEntity.ok(jobBoardService.register(name, request));
    }


    @GetMapping("/post/all")
    public ResponseEntity<List<RegisterJobBoardResponse>> findAll(){
        return ResponseEntity.ok(jobBoardService.findAll());
    }
}
