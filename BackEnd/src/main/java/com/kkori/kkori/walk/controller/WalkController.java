package com.kkori.kkori.walk.controller;

import com.kkori.kkori.walk.dto.WalkDetailResponse;
import com.kkori.kkori.walk.dto.WalkRequest;
import com.kkori.kkori.walk.dto.WalkResponse;
import com.kkori.kkori.walk.service.WalkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/detail/{walkId}")
    public ResponseEntity<WalkDetailResponse> detail(
            @PathVariable Long walkId
    ){
        return ResponseEntity.ok(walkService.walkDetail(walkId));
    }

    @GetMapping("/detail/by-sitter/{postId}")
    public ResponseEntity<WalkDetailResponse> walkDetail(
            @PathVariable Long postId
    ){
        return ResponseEntity.ok(walkService.walkDetail(postId));

    }

    @GetMapping("/detail/by-member/{dogId}")
    public ResponseEntity<List<WalkDetailResponse>> walkDetailByMember(
            @PathVariable Long dogId
    ){
        return ResponseEntity.ok(walkService.findAllByDog(dogId));

    }
}
