package org.jesperancinha.ktd.delegates;

import java.time.LocalDateTime;

public interface Card {
    LocalDateTime expiryDate();

    Long cardNumber();
}
