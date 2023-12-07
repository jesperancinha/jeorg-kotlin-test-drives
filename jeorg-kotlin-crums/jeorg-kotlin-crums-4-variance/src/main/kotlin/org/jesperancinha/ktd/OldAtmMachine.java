package org.jesperancinha.ktd;

import jakarta.annotation.Nonnull;
import org.jetbrains.annotations.NotNull;

public class OldAtmMachine {

    public void cashIn(Long value){
        System.out.printf("Cashed in %d%n", value);
    }
    public void cashInNotNullJetBrains(@NotNull Long value){
        System.out.printf("Cashed in %d%n", value);
    }

    public void cashInNotNullJakartaValidation(@jakarta.validation.constraints.NotNull Long value){
        System.out.printf("Cashed in %d%n", value);
    }
    public void cashInNotNullEclipse(@org.eclipse.jdt.annotation.NonNull Long value){
        System.out.printf("Cashed in %d%n", value);
    }
    public void cashInJakartaAnnotation(@Nonnull Long value){
        System.out.printf("Cashed in %d%n", value);
    }
}
