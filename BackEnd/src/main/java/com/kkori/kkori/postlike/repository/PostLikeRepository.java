package com.kkori.kkori.postlike.repository;

import com.kkori.kkori.postlike.entity.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostLikeRepository extends JpaRepository<PostLike,Long> {
}
