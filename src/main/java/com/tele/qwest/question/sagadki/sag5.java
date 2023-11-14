package com.tele.qwest.question.sagadki;

import com.tele.qwest.question.AbstractQuestion;

public class sag5 extends AbstractQuestion {
    public sag5() {
        super("Стоит дуб,\n" +
                "В нем двенадцать гнезд,\n" +
                "В каждом гнезде\n" +
                "По четыре яйца,\n" +
                "В каждом яйце\n" +
                "По семи цыпленков.", "Год", 2, 4);
    }

    @Override
    public boolean checkAnswer(String answer) {
        return answer.equalsIgnoreCase("Год");
    }
}
