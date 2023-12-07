package com.yuiko.study.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Flower {
    private long id;
    private long userId;
    private String flowerSpecies;
    private String soil;
    private String fertilizerType;
    private String waterType;
    private double height;
}
