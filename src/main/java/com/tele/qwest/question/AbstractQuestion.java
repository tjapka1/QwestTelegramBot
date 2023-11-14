package com.tele.qwest.question;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class AbstractQuestion {

    private final String question;
    private final String answer;
    private final int plusBall;
    private final int minusBall;

public abstract boolean checkAnswer(String answer);
}
