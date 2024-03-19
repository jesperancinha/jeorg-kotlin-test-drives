package org.jesperancinha.ktd.crums3.goldengirls.javaversion;

import lombok.val;

public class GoldenGirlsJavaRunner {
    public static void main(String[] args) {
        var goldenGirlsJava = new GoldenGirlsJava(
                "Dorothy Zbornak",
                "Rose Nylund",
                "Blanche Devereaux",
                "Sophia Petrillo"
        );
        System.out.println(goldenGirlsJava);
        alternative();
    }

    public static void alternative(){
        val goldenGirlsJava = new GoldenGirlsJava(
                "Dorothy Zbornak",
                "Rose Nylund",
                "Blanche Devereaux",
                "Sophia Petrillo"
        );
        val goldenGirlsJavaSimple = new GoldenGirlsJava();
        System.out.println(goldenGirlsJava);
        goldenGirlsJava.setGoldenGirl1("Blanche Devereaux");
        System.out.println(goldenGirlsJava);
        System.out.println(goldenGirlsJavaSimple);
    }
}
