package com.kkori.kkori.reservedhistory.controller;


import com.kkori.kkori.jobboard.dto.RegisterJobBoardResponse;
import com.kkori.kkori.reservedhistory.dto.ReservedHistoryResponse;
import com.kkori.kkori.reservedhistory.service.ReservedHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    // 이건 이제 예약하는 api 인데 산책시키는 사람 이메일이랑 게시물 id 로 예약하는 api
    // sitter? 이메일을 알면 됨 sitter= 산책시키는 사람
    // 근데 email 아니여도 돼 그건 상관없음
    @GetMapping("/qr")
    public ResponseEntity<List<RegisterJobBoardResponse>> findAllBySitterAndMember(
            final Authentication authentication,
            @RequestParam String qrCode
    ){
        Long sitterId = Long.parseLong(authentication.getName());

        return ResponseEntity.ok(reservedHistoryService.findAllByMemberAndSitter(sitterId,qrCode));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ReservedHistoryResponse>> findAllByMember(
            final Authentication authentication
    ){

        return ResponseEntity.ok(reservedHistoryService.findAllReservedHistory(Long.parseLong(authentication.getName())));

    }

    @PutMapping("/completed/{reservedHistoryId}")
    public ResponseEntity<?> makeCompleted (
            @PathVariable Long reservedHistoryId
    ){
        reservedHistoryService.makeCompleted(reservedHistoryId);
        return ResponseEntity.ok().build();
    }


}
