package org.jesperancinha.ktd.crums3.goldengirls.javaversion;

import lombok.val;

public class GoldenGirlsRecordRunner {
    public static void main(String[] args) {
        var goldenGirlsRecord = new GoldenGirlsRecord(
                "Dorothy Zbornak",
                "Rose Nylund",
                "Blanche Devereaux",
                "Sophia Petrillo"
        );
        System.out.println(goldenGirlsRecord);
        alternative();
    }

    public static void alternative(){
        val goldenGirlsRecord = new GoldenGirlsRecord(
                "Dorothy Zbornak",
                "Rose Nylund",
                "Blanche Devereaux",
                "Sophia Petrillo"
        );
        val goldenGirlsRecordSimple = new GoldenGirlsRecord();
        System.out.println(goldenGirlsRecord);
        System.out.println(goldenGirlsRecord);
        System.out.println(goldenGirlsRecordSimple);
    }
}
