package com.tele.qwest.question.programming;

import com.tele.qwest.question.AbstractQuestion;

public class HTTPQuestion extends AbstractQuestion {

    private final int minimalCount = 1;
    private final String[] httpMethods = {"GET", "POST", "HEAD", "PUT", "PATCH", "DELETE", "OPTIONS", "CONNECT", "TRACE"};
    public HTTPQuestion() {
        super("03 Перечислите, пожалуйста, все известные вам методы HTTP - запросов. Минимум 4 метода",
                "GET, POST, HEAD, PUT, PATCH, DELETE, OPTIONS, CONNECT, TRACE", 5, 2);
    }

    @Override
    public boolean checkAnswer(String answer) {

        int count = 0;
        for (String method : httpMethods){
            if (answer.toUpperCase().contains(method)){
                count++;
            }
        }

        return count>=minimalCount;
    }
}
