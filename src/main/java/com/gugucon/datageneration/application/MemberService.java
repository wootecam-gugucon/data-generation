package com.gugucon.datageneration.application;

import com.gugucon.datageneration.domain.Member;
import com.gugucon.datageneration.generator.Generator;
import com.gugucon.datageneration.repository.MemberRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final Generator<Member> memberGenerator;

    public int createMember(final int number) {
        List<Member> members = memberGenerator.generate(number);
        memberRepository.saveAll(members);
        return members.size();
    }

}
