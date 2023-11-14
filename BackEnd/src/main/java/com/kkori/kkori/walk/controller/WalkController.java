package com.kkori.kkori.walk.controller;

import com.kkori.kkori.walk.dto.WalkRequest;
import com.kkori.kkori.walk.dto.WalkResponse;
import com.kkori.kkori.walk.service.WalkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/walk")
public class WalkController {

    private final WalkService walkService;

    @PostMapping("/register")
    public ResponseEntity<WalkResponse> registerWalk(
            @RequestBody WalkRequest walkRequest,
            final Authentication authentication
            ){
        long sitterId = Long.parseLong(authentication.getName());
        return ResponseEntity.ok(walkService.registerWalk(sitterId,walkRequest));

    }
}
