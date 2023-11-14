package com.tele.qwest.question.sagadki;

import com.tele.qwest.question.AbstractQuestion;

public class sag10 extends AbstractQuestion {
    public sag10() {
        super("Вот иголки и булавки\n" +
                "Выползают из-под лавки,\n" +
                "На меня они глядят,\n" +
                "Молока они хотят.", "Ёж", 2, 4);
    }

    @Override
    public boolean checkAnswer(String answer) {
        return answer.equalsIgnoreCase("Ёж");
    }
}
