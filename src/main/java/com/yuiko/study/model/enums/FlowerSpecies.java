package com.yuiko.study.model.enums;

public enum FlowerSpecies {
    rose("rose"),
    tulip("tilip"),
    sunflower("sunflower"),
    lavender("lavender"),
    oak_tree("oak_tree"),
    daisy("daisy"),
    maple_tree("maple_tree"),
    cactus("cactus"),
    fern("fern"),
    daffodil("daffodil");

    private final String name;

    FlowerSpecies(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
