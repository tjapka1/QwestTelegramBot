package com.tele.qwest.question.programming;

import com.tele.qwest.question.AbstractQuestion;

public class GitQuestion extends AbstractQuestion {
    public GitQuestion() {
        super("04 С помощью какой команды в Git можно посмотреть информацию об авторстве строки кода в том или ином файле?", "blame", 2,5);
    }

    @Override
    public boolean checkAnswer(String answer) {
        return answer.equalsIgnoreCase("blame");
    }
}
