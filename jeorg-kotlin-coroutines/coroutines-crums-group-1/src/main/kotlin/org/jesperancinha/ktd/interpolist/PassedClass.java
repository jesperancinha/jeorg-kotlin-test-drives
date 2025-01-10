package org.jesperancinha.ktd.interpolist;

import java.util.List;

public class PassedClass {
    public static void changeList(List<String> all) {
        all.add("AAAA");
    }

    public static void changeListMutable(List<String> all) {
        all.add("AAAA");
    }
}
