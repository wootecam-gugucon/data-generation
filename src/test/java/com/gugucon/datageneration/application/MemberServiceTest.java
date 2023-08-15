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
        memberRepository.deleteAll();
        int count = memberService.createMember(1000);
        assertThat(memberRepository.count()).isEqualTo(count);
    }

    @Test
    void deleteAll() {
        memberRepository.deleteAll();
        assertThat(memberRepository.count()).isZero();
    }
}