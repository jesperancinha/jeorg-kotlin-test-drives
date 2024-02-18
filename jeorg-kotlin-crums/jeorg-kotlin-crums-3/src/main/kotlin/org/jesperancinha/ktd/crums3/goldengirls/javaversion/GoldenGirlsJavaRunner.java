package org.jesperancinha.ktd.crums3.goldengirls.javaversion;

import lombok.val;

public class GoldenGirlsJavaRunner {
    public static void main(String[] args) {
        var golderGirlsJava = new GoldenGirlsJava(
                "Dorothy Zbornak",
                "Rose Nylund",
                "Blanche Devereaux",
                "Sophia Petrillo"
        );
        System.out.println(golderGirlsJava);
        alternative();
    }

    public static void alternative(){
        val golderGirlsJava = new GoldenGirlsJava(
                "Dorothy Zbornak",
                "Rose Nylund",
                "Blanche Devereaux",
                "Sophia Petrillo"
        );
        System.out.println(golderGirlsJava);
        golderGirlsJava.setGoldenGirl1("Blanche Devereaux");
        System.out.println(golderGirlsJava);
    }
}
