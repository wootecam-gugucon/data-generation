package com.gugucon.datageneration.application;

import static org.assertj.core.api.Assertions.assertThat;

import com.gugucon.datageneration.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    void createMember() {
        // given
        memberRepository.deleteAllInBatch();

        // when
        int count = memberService.createMember(1000);

        // then
        assertThat(memberRepository.count()).isEqualTo(count);
    }

    @Test
    void deleteAll() {
        // when
        memberRepository.deleteAllInBatch();

        // then
        assertThat(memberRepository.count()).isZero();
    }
}