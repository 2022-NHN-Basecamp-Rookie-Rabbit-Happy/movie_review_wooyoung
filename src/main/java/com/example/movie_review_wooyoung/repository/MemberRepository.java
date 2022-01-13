package com.example.movie_review_wooyoung.repository;

import com.example.movie_review_wooyoung.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
