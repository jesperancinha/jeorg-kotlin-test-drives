package org.jesperancinha.ktd.nonomads;

import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class FunctorTestJava {

    @Test
    public void test_whenMapOfMaps_seeDecompiler() {
        var newTree = DomainKt.getTreeCollection()
                .stream()
                .map(tree -> tree.getLeaves())
                .map(leaves -> new Tree(leaves))
                        .collect(Collectors.toList());
       assertThat(newTree).hasSize(10);
    }
}
