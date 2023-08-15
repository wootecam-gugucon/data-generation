package com.gugucon.datageneration.generator;

import java.util.List;

public interface Generator<T> {

    List<T> generate(final int number);
}
