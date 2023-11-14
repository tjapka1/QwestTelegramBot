package com.tele.qwest.question.programming;

import com.tele.qwest.question.AbstractQuestion;

public class SQLQuestion extends AbstractQuestion {
    public SQLQuestion() {
        super("02 Сколько основных типов связующих таблиц существует в реляционных базах данных?", "3", 3, 6);
    }

    @Override
    public boolean checkAnswer(String answer) {
        return answer.equals("3");
    }
}
