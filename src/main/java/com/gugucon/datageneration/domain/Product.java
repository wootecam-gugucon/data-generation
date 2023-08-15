package com.gugucon.datageneration.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@ToString
public class Product extends BaseTimeEntity {

    @Id
    private Long id;

    private String name;

    private Long price;

    private String imageFileName;

    private Integer stock;

    @Lob
    @Column(length = 1000)
    private String description;
}
