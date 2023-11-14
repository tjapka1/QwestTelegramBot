package com.tele.qwest.question.sagadki;

import com.tele.qwest.question.AbstractQuestion;

public class sag8 extends AbstractQuestion {
    public sag8() {
        super("Музыкант, певец, рассказчик —\n" +
                "А всего труба да ящик.", "Граммофон", 2, 4);
    }

    @Override
    public boolean checkAnswer(String answer) {
        return answer.equalsIgnoreCase("Граммофон");
    }
}
