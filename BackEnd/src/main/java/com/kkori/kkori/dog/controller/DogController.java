package com.kkori.kkori.dog.controller;

import com.kkori.kkori.dog.dto.RegisterDogRequest;
import com.kkori.kkori.dog.dto.RegisterDogResponse;
import com.kkori.kkori.dog.dto.UpdateDogRequest;
import com.kkori.kkori.dog.dto.UpdateDogResponse;
import com.kkori.kkori.dog.service.DogService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dog")
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

    @GetMapping("/all/by-member")
    public ResponseEntity<List<RegisterDogResponse>> findAllByMemberId(
            final Authentication authentication
    ){
        long memberId = Long.parseLong(authentication.getName());

        return ResponseEntity.ok(dogService.findAllDogByMemberId(memberId));
    }

    @PutMapping("/modify/{dogId}")
    public ResponseEntity<UpdateDogResponse> updateDog(
            @PathVariable Long dogId,
            final Authentication authentication,
            @RequestBody UpdateDogRequest updateDogRequest
            ){

        long memberId = Long.parseLong(authentication.getName());

        return ResponseEntity.ok(dogService.updateDog(memberId,dogId,updateDogRequest));
    }
}
