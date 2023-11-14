package com.tele.qwest.question.sagadki;

import com.tele.qwest.question.AbstractQuestion;

public class sag3 extends AbstractQuestion {
    public sag3() {
        super("Интересный мяч какой:\n" +
                "Покрути его рукой\n" +
                "И увидишь океаны,\n" +
                "Горы и моря, и страны,\n" +
                "Антарктиду и Гаити.\n" +
                "Что за мяч такой, скажите?", "Глобус", 2, 4);
    }

    @Override
    public boolean checkAnswer(String answer) {
        return answer.equalsIgnoreCase("Глобус");
    }
}
