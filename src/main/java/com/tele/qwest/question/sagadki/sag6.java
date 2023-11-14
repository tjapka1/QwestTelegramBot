package com.tele.qwest.question.sagadki;

import com.tele.qwest.question.AbstractQuestion;

public class sag6 extends AbstractQuestion {
    public sag6() {
        super("Вдруг из черной темноты\n" +
                "В небе выросли кусты.\n" +
                "А на них-то голубые,\n" +
                "Пунцовые, золотые\n" +
                "Распускаются цветы\n" +
                "Небывалой красоты.\n" +
                "И все улицы под ними\n" +
                "Тоже стали голубыми,\n" +
                "Пунцовыми, золотыми,\n" +
                "Разноцветными.", "Салют", 2, 4);
    }

    @Override
    public boolean checkAnswer(String answer) {
        return answer.equalsIgnoreCase("Салют");
    }
}
