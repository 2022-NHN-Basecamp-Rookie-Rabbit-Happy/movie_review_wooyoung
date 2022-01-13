package com.example.movie_review_wooyoung.repository;

import com.example.movie_review_wooyoung.entity.Member;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void insertMembers() {

        IntStream.rangeClosed(1, 100).forEach(i -> {
            Member member = Member.builder()
                .email("r" + i + "@zerock.org")
                .pw("1111")
                .nickname("erviewer" + i).build();

            memberRepository.save(member);
        });
    }
}