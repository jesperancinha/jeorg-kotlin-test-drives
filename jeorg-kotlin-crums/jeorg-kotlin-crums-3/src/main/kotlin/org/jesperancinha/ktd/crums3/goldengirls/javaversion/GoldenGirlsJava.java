package org.jesperancinha.ktd.crums3.goldengirls.javaversion;

public class GoldenGirlsJava {

    public String goldenGirl1;

    private final String goldenGirl2;

    private final String goldenGirl3;

    private final String goldenGirl4;


    public GoldenGirlsJava() {
        this.goldenGirl1 = "Dorothy Zbornak";
        this.goldenGirl2 = "Rose Nylund";
        this.goldenGirl3 = "Blanche Devereaux";
        this.goldenGirl4 = "Sophia Petrillo";

    }

    public GoldenGirlsJava(
            String goldenGirl1,
            String goldenGirl2,
            String goldenGirl3,
            String goldenGirl4
    ) {
        this.goldenGirl1 = goldenGirl1;
        this.goldenGirl2 = goldenGirl2;
        this.goldenGirl3 = goldenGirl3;
        this.goldenGirl4 = goldenGirl4;
    }

    public String getGoldenGirl1() {
        return goldenGirl1;
    }

    public void setGoldenGirl1(String goldenGirl1) {
        this.goldenGirl1 = goldenGirl1;
    }

    public String getGoldenGirl2() {
        return goldenGirl2;
    }

    public String getGoldenGirl3() {
        return goldenGirl3;
    }

    public String getGoldenGirl4() {
        return goldenGirl4;
    }

    @Override
    public String toString() {
        return "GoldenGirlsJava{" +
                "goldenGirl1='" + goldenGirl1 + '\'' +
                ", goldenGirl2='" + goldenGirl2 + '\'' +
                ", goldenGirl3='" + goldenGirl3 + '\'' +
                ", goldenGirl4='" + goldenGirl4 + '\'' +
                '}';
    }
}
