package com.kkori.kkori.dog.controller;

import com.kkori.kkori.dog.dto.RegisterDogRequest;
import com.kkori.kkori.dog.dto.RegisterDogResponse;
import com.kkori.kkori.dog.service.DogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/device")
@RequiredArgsConstructor
public class DogController {

    private final DogService dogService;

    @PostMapping("/register-dog")
    public ResponseEntity<RegisterDogResponse> registerDog(
            final Authentication authentication,
            @RequestBody RegisterDogRequest request
            ){

        long memberId = Long.parseLong(authentication.getName());
        return ResponseEntity.ok(dogService.registerDog(memberId,request));
    }
}
