package org.jesperancinha.ktd.crums3.goldengirls.javaversion;

import lombok.*;

public class GoldenGirlsLombokRunner {

    public static void main(String[] args) {
        var goldenGirlsLombok = new GoldenGirlsLombok(
                "Dorothy Zbornak",
                "Rose Nylund",
                "Blanche Devereaux",
                "Sophia Petrillo"
        );
        System.out.println(goldenGirlsLombok);
        alternative();
    }

    public static void alternative(){
        val goldenGirlsLombok = new GoldenGirlsLombok(
                "Dorothy Zbornak",
                "Rose Nylund",
                "Blanche Devereaux",
                "Sophia Petrillo"
        );
        val goldenGirlsLombokSimple = new GoldenGirlsLombok();
        System.out.println(goldenGirlsLombok);
        goldenGirlsLombok.setGoldenGirl1("Blanche Devereaux");
        System.out.println(goldenGirlsLombok);
        System.out.println(goldenGirlsLombokSimple);
    }
}
