package org.jesperancinha.ktd.delegates;

import java.time.LocalDateTime;

public record DebitCard(Long cardNumber, LocalDateTime expiryDate) implements Card{}
