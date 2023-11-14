package com.tele.qwest.question.sagadki;

import com.tele.qwest.question.AbstractQuestion;

public class sag7 extends AbstractQuestion {
    public sag7() {
        super("Речка спятила с ума —\n" +
                "По домам пошла сама.", "Водопровод", 2, 4);
    }

    @Override
    public boolean checkAnswer(String answer) {
        return answer.equalsIgnoreCase("Водопровод");
    }
}
