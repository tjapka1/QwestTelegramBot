package com.tele.qwest.question;

public class JavaQuestion extends AbstractQuestion {
    public JavaQuestion() {
        super("01 Сколько в языке программирование Java существует примитивов?", "8", 3, 6);
    }

    public boolean checkAnswer(String answer){
        return (answer.equalsIgnoreCase("8"));
    }
}
