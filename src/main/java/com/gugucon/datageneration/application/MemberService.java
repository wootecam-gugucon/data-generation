package com.gugucon.datageneration.application;

import com.gugucon.datageneration.domain.Member;
import com.gugucon.datageneration.generator.MemberGenerator;
import com.gugucon.datageneration.repository.MemberRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberGenerator memberGenerator;

    public int createMember(final int number) {
        final List<Member> members = memberGenerator.generate(number);
        members.stream()
               .parallel()
               .forEach(memberRepository::save);
        return members.size();
    }

}
