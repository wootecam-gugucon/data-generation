package com.gugucon.datageneration.application;

import com.gugucon.datageneration.generator.MemberGenerator;
import com.gugucon.datageneration.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberGenerator memberGenerator;

    public long createMember(final int count) {
        IntStream.range(0, count)
                .parallel()
                .mapToObj(i -> memberGenerator.generate())
                .forEach(memberRepository::save);

        return memberRepository.count();
    }
}
