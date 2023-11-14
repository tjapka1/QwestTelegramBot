package com.tele.qwest.question.sagadki;

import com.tele.qwest.question.AbstractQuestion;

public class sag2 extends AbstractQuestion {
    public sag2() {
        super("Кто его раздеваетб тот слёзы пролевает", "Лук", 2, 4);
    }

    @Override
    public boolean checkAnswer(String answer) {
        return answer.equalsIgnoreCase("Лук");
    }
}
