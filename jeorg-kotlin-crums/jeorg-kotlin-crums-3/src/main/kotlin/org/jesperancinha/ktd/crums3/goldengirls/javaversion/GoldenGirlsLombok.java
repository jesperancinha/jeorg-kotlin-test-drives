package org.jesperancinha.ktd.crums3.goldengirls.javaversion;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class GoldenGirlsLombok {

    private final String goldenGirl1;

    private final String goldenGirl2;

    private final String goldenGirl3;

    private final String goldenGirl4;

    public GoldenGirlsLombok() {
        this.goldenGirl1 = "Dorothy Zbornak";
        this.goldenGirl2 = "Rose Nylund";
        this.goldenGirl3 = "Blanche Devereaux";
        this.goldenGirl4 = "Sophia Petrillo";
    }

    public String getGoldenGirl1() {
        return goldenGirl1;
    }
}
