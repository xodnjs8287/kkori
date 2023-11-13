package com.kkori.kkori.dog.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.kkori.kkori.dog.dto.*;
import com.kkori.kkori.dog.service.DogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/dog")
@RequiredArgsConstructor
public class DogController {

    private final DogService dogService;

    @PostMapping("/register-dog")
    public ResponseEntity<RegisterDogResponse> registerDog(
            Authentication authentication,
            @RequestPart("dog") String dogStr,
            @RequestPart("image") MultipartFile image) throws IOException {

        long memberId = Long.parseLong(authentication.getName());

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        RegisterDogRequest request = objectMapper.readValue(dogStr, RegisterDogRequest.class);
        request.setImage(image);

        RegisterDogResponse response = dogService.registerDog(memberId, request);
        return ResponseEntity.ok(response);
    }


    @PutMapping("/modify/{dogId}")
    public ResponseEntity<UpdateDogResponse> updateDog(
            @PathVariable Long dogId,
            Authentication authentication,
            @RequestPart("dog") String dogStr,
            @RequestPart("image") MultipartFile image
    ) throws JsonProcessingException {

        long memberId = Long.parseLong(authentication.getName());

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        UpdateDogRequest request = objectMapper.readValue(dogStr, UpdateDogRequest.class);
        request.setImage(image);

        UpdateDogResponse updateDogResponse = dogService.updateDog(memberId, dogId, request);

        return ResponseEntity.ok(updateDogResponse);
    }

    @GetMapping("/all/lost-dog")
    public ResponseEntity<List<RegisterDogResponse>> findAllLostDog(){
        return ResponseEntity.ok(dogService.findAllLostDogs());
    }

    @GetMapping("/all/by-member")
    public ResponseEntity<List<RegisterDogResponse>> findAllByMemberId(
            final Authentication authentication
    ) {
        long memberId = Long.parseLong(authentication.getName());

        return ResponseEntity.ok(dogService.findAllDogByMemberId(memberId));
    }

    @GetMapping("/detail/{dogId}")
    public ResponseEntity<RegisterDogResponse> dogDetail(
            @PathVariable Long dogId
    ) {
        return ResponseEntity.ok(dogService.dogDetail(dogId));
    }

    @PutMapping("/register-lost/{dogId}")
    public ResponseEntity<LostDogDto> registerLostDog(
            @PathVariable Long dogId,
            final Authentication authentication
    ) {
        return ResponseEntity.ok(dogService.registerLostDog(Long.parseLong(authentication.getName()), dogId));

    }

    @DeleteMapping("/delete/{dogId}")
    public ResponseEntity<?> deleteDog(
            @PathVariable Long dogId,
            final Authentication authentication
    ) {
        long memberId = Long.parseLong(authentication.getName());

        try {
            dogService.deleteDog(memberId, dogId);
            return ResponseEntity.ok("강아지 정보가 삭제됨 ");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/all-lostdog/by-member")
    public ResponseEntity<List<RegisterDogResponse>> findAllLostDogsByMember(
            final Authentication authentication
    ){
        long l = Long.parseLong(authentication.getName());
        return ResponseEntity.ok(dogService.findAllLostDogByMemberId(l));
    }

    @GetMapping("/all-not-lostdog/by-member")
    public ResponseEntity<List<RegisterDogResponse>> findAllNotLostDogsByMember(
            final Authentication authentication
    ){
        long l = Long.parseLong(authentication.getName());
        return ResponseEntity.ok(dogService.findAllNotLostDogByMemberId(l));
    }


}
