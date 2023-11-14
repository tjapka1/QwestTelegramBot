package com.tele.qwest.question.sagadki;

import com.tele.qwest.question.AbstractQuestion;

public class sag9 extends AbstractQuestion {
    public sag9() {
        super("Из стены торчу,\n" +
                "Головой кручу,\n" +
                "Мою и пою\n" +
                "Целую семью.", "Водопроводный кран", 2, 4);
    }

    @Override
    public boolean checkAnswer(String answer) {
        return answer.equalsIgnoreCase("Водопроводный кран");
    }
}
