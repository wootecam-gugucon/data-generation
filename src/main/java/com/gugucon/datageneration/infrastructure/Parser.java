package com.gugucon.datageneration.infrastructure;

public interface Parser<T> {

    T parse(String str);
}
