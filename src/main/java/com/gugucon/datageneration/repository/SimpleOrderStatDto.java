package com.gugucon.datageneration.repository;

import lombok.Getter;

@Getter
public class SimpleOrderStatDto {

    private Long productId;
    private Long count;

    public SimpleOrderStatDto(Long productId, Long count) {
        this.productId = productId;
        this.count = count;
    }
}
