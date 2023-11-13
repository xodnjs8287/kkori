package com.kkori.kkori.jobboardlike.controller;

import com.kkori.kkori.jobboard.dto.RegisterJobBoardResponse;
import com.kkori.kkori.jobboardlike.service.JobBoardLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/job-board")
public class JobBoardLikeController {

    private final JobBoardLikeService jobBoardLikeService;

    @PostMapping("/like/{jobBoardId}")
    public ResponseEntity<RegisterJobBoardResponse> addLike(@PathVariable Long jobBoardId,
                                                            final Authentication authentication){

        long memberId = Long.parseLong(authentication.getName());
        return ResponseEntity.ok(jobBoardLikeService.addLike(memberId,jobBoardId));
    }

    @DeleteMapping("/like/delete-like/{jobBoardId}")
    public ResponseEntity<Void> removeLike( @PathVariable Long jobBoardId,
                                           final Authentication authentication){
        long memberId = Long.parseLong(authentication.getName());

        jobBoardLikeService.removeLike(memberId,jobBoardId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/like/all")
    public ResponseEntity<List<RegisterJobBoardResponse>> findAllLikePosts(
            final Authentication authentication
    ) {
        long memberId = Long.parseLong(authentication.getName());

        return ResponseEntity.ok(jobBoardLikeService.findAllLikeJobBoard(memberId));
    }
}
