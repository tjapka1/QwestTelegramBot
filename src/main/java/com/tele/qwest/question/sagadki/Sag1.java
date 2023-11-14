package com.tele.qwest.question.sagadki;

import com.tele.qwest.question.AbstractQuestion;

public class Sag1 extends AbstractQuestion {
    public Sag1() {
        super("Висит груша, нельзя скушать", "Лампочка", 2, 4);
    }

    @Override
    public boolean checkAnswer(String answer) {
        return answer.equalsIgnoreCase("Лампочка");
    }
}
