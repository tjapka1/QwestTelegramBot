package com.tele.qwest.question.sagadki;

import com.tele.qwest.question.AbstractQuestion;

public class sag4 extends AbstractQuestion {
    public sag4() {
        super("В Полотняной стране\n" +
                "По реке Простыне\n" +
                "Плывет пароход\n" +
                "То назад, то вперед,\n" +
                "А за ним такая гладь —\n" +
                "Ни морщинки не видать.", "Утюг", 2, 4);
    }

    @Override
    public boolean checkAnswer(String answer) {
        return answer.equalsIgnoreCase("Утюг");
    }
}
