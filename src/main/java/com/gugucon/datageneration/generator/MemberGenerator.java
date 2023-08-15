package com.gugucon.datageneration.generator;

import com.gugucon.datageneration.domain.Member;
import com.gugucon.datageneration.utils.RandomStringUtils;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class MemberGenerator implements Generator<Member> {

    private static final int DOMAIN_MIN_LENGTH = 5;
    private static final int DOMAIN_MAX_LENGTH = 8;
    private static final int PROVIDER_MIN_LENGTH = 2;
    private static final int PROVIDER_MAX_LENGTH = 5;
    private static final int PASSWORD_MIN_LENGTH = 4;
    private static final int PASSWORD_MAX_LENGTH = 20;
    private static final int NICKNAME_MIN_LENGTH = 3;
    private static final int NICKNAME_MAX_LENGTH = 10;

    private static final String EMAIL_REGEX = "^[\\w]+@[a-zA-Z0-9]+\\.[a-zA-Z.]{2,}$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
    private static final String PASSWORD_REGEX = "^(?=.*[a-z]).{4,20}$";
    private static final Pattern PASSWORD_PATTERN = Pattern.compile(PASSWORD_REGEX);

    private final Random random = new Random();

    @Override
    public List<Member> generate(final int number) {
        return IntStream.range(0, number)
                        .mapToObj(i -> Member.builder()
                                             .email(emailGenerate())
                                             .password(passwordGenerate())
                                             .nickname(nicknameGenerate())
                                             .build())
                        .toList();
    }

    private String emailGenerate() {
        String id = LocalDateTime.now().toString().replaceAll("[-:.]", "");
        String domain = RandomStringUtils.randomAlphanumeric(random.nextInt(DOMAIN_MIN_LENGTH, DOMAIN_MAX_LENGTH));
        String tld = RandomStringUtils.randomAlphabetic(random.nextInt(PROVIDER_MIN_LENGTH, PROVIDER_MAX_LENGTH));
        return validateEmail(id+"@"+domain+"."+tld);
    }

    private String validateEmail(final String email) {
        final Matcher matcher = EMAIL_PATTERN.matcher(email);
        if (!matcher.matches()) {
            throw new IllegalStateException("이메일 형식에 맞지 않습니다. " + email);
        }
        return email;
    }

    private String passwordGenerate() {
        String password = RandomStringUtils.randomAlphanumeric(random.nextInt(PASSWORD_MIN_LENGTH, PASSWORD_MAX_LENGTH))
                + RandomStringUtils.randomAlphabetic(1);
        return validatePassword(password);
    }

    private String validatePassword(final String password) {
        final Matcher matcher = PASSWORD_PATTERN.matcher(password);
        if (!matcher.matches()) {
            throw new IllegalStateException("비밀번호 형식에 맞지 않습니다. " + password);
        }
        return password;
    }

    private String nicknameGenerate() {
        return RandomStringUtils.randomAlphabetic(random.nextInt(NICKNAME_MIN_LENGTH, NICKNAME_MAX_LENGTH));
    }
}
