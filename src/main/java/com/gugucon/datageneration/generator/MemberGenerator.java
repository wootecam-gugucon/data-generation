package com.gugucon.datageneration.generator;

import com.gugucon.datageneration.domain.Gender;
import com.gugucon.datageneration.domain.Member;
import com.gugucon.datageneration.utils.RandomStringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class MemberGenerator {

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
    private static final LocalDate START_DATE = LocalDate.of(1950, 1, 1);
    private static final LocalDate END_DATE = LocalDate.of(2010, 12, 31);

    private final Random random = new Random();
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public Member generate() {
        Gender[] gender = Gender.values();
        long daysBetween = ChronoUnit.DAYS.between(START_DATE, END_DATE);

        return Member.builder()
                .email(generateEmail())
                .password(passwordEncoder.encode(generatePassword()))
                .nickname(generateNickname())
                .gender(gender[random.nextInt(gender.length)])
                .birthDate(START_DATE.plusDays(random.nextInt((int) daysBetween + 1)))
                .build();
    }

    private String generateEmail() {
        String id = LocalDateTime.now().toString().replaceAll("[-:.]", "");
        String domain = RandomStringUtils.randomAlphanumeric(random.nextInt(DOMAIN_MIN_LENGTH, DOMAIN_MAX_LENGTH));
        String tld = RandomStringUtils.randomAlphabetic(random.nextInt(PROVIDER_MIN_LENGTH, PROVIDER_MAX_LENGTH));
        return validateEmail(id + "@" + domain + "." + tld);
    }

    private String validateEmail(final String email) {
        final Matcher matcher = EMAIL_PATTERN.matcher(email);
        if (!matcher.matches()) {
            throw new IllegalStateException("이메일 형식에 맞지 않습니다. " + email);
        }
        return email;
    }

    private String generatePassword() {
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

    private String generateNickname() {
        return RandomStringUtils.randomAlphabetic(random.nextInt(NICKNAME_MIN_LENGTH, NICKNAME_MAX_LENGTH));
    }
}
