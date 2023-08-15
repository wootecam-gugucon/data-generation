package com.gugucon.datageneration.utils;

import java.util.Random;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RandomStringUtils {

    private static final int ASCII_LARGE_A = 97;
    private static final int ASCII_SMALL_Z = 122;
    private static final Random RANDOM = new Random();

    public static String randomAlphanumeric(final int length) {
        UUID randomUUID = UUID.randomUUID();

        return randomUUID.toString()
                         .replace("-", "")
                         .substring(0, length);
    }

    public static String randomAlphabetic(final int length) {
        return RANDOM.ints(ASCII_LARGE_A, ASCII_SMALL_Z + 1)
                     .limit(length)
                     .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                     .toString();
    }

}
