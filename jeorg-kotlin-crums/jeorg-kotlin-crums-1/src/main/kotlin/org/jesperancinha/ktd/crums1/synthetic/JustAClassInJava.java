package org.jesperancinha.ktd.crums1.synthetic;

public class JustAClassInJava {
    public static void main(String[] args) {
        SyntheticKotlin myClass = new SyntheticKotlin();
        myClass.publicFunction();
        // This will not work because this function is synthetic
        // myClass.syntheticKotlinFunction();
    }
}
