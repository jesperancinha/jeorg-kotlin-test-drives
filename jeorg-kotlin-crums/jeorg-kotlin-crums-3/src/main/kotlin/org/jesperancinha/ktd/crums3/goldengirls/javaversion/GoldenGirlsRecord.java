package org.jesperancinha.ktd.crums3.goldengirls.javaversion;

public record GoldenGirlsRecord(
        String goldenGirl1,
        String goldenGirl2,
        String goldenGirl3,
        String goldenGirl4
) {
    public GoldenGirlsRecord() {
        this(
                "Dorothy Zbornak",
                "Rose Nylund",
                "Blanche Devereaux",
                "Sophia Petrillo"
        );
    }
}
