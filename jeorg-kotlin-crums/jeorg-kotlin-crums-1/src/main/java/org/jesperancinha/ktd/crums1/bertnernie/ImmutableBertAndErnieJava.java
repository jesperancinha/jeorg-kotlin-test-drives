package org.jesperancinha.ktd.crums1.bertnernie;

public class ImmutableBertAndErnieJava {

    public String helloBert() {
        final var helloBert = "Hello! I'm Bert";
        final String helloErnie = "and I'm Ernie";
        return helloBert.concat(" ").concat(helloErnie);
    }

    public String helloBertClean(){
        return "Hello! I'm Bert and I'm Ernie";
    }
}
