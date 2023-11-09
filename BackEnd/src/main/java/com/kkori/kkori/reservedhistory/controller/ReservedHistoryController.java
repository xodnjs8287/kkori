package com.kkori.kkori.reservedhistory.controller;


import com.kkori.kkori.reservedhistory.dto.ReservedHistoryResponse;
import com.kkori.kkori.reservedhistory.service.ReservedHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reserved-job")
@RequiredArgsConstructor
public class ReservedHistoryController {

    private final ReservedHistoryService reservedHistoryService;

    @PostMapping("/reserve/{sitterEmail}/{postId}")
    public ResponseEntity<ReservedHistoryResponse> makeReserve (
            @PathVariable String sitterEmail,
            @PathVariable Long postId,
            final Authentication authentication
            ){
        return ResponseEntity.ok(reservedHistoryService.makeReserved(sitterEmail,Long.parseLong(authentication.getName()),postId));
    }

    @PutMapping("/completed/{reservedHistoryId}")
    public ResponseEntity<?> makeCompleted (
            @PathVariable Long reservedHistoryId
    ){
        reservedHistoryService.makeCompleted(reservedHistoryId);
        return ResponseEntity.ok().build();
    }

}
