package com.kkori.kkori.postlikes.repository;

import com.kkori.kkori.postlikes.entity.PostLikes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostLikeRepository extends JpaRepository<PostLikes,Long> {
}
