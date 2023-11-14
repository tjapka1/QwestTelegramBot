package com.tele.qwest.service;

import com.tele.qwest.Config.BotConfig;
import com.tele.qwest.Entity.User;
import com.tele.qwest.Repository.UserRepository;
import com.tele.qwest.question.*;
import com.tele.qwest.question.programming.GitQuestion;
import com.tele.qwest.question.programming.HTTPQuestion;
import com.tele.qwest.question.programming.SQLQuestion;
import com.tele.qwest.question.sagadki.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class TelegramBot extends TelegramLongPollingBot {
    @Autowired
    BotConfig config;
    private boolean quizStart = false;
    private AbstractQuestion[] questions;
    static final String HELP_TEXT = "This bot is created to demonstrate Spring capabilities. \n\n" +
            "You can execute commands from the main menu on the left or by typing a command: \n\n" +
            "Type /start to see a welcome message\n\n"+
            "Type /question to start the Quiz\n\n"+
            "Type /setNull set quiz off null\n\n"+
            "Type /mydata get my data\n\n"+
            "Type /deletedata delete my data\n\n"+
            "Type /help to see this message again"
            ;

    public TelegramBot(BotConfig config) {
        this.config = config;
        questions = getQuestions();
        List<BotCommand>listOfCommands=new ArrayList<>();
        listOfCommands.add(new BotCommand("/start", "get start"));
        listOfCommands.add(new BotCommand("/question", "Start the Quiz"));
        listOfCommands.add(new BotCommand("/setNull", "set quiz off null"));
        listOfCommands.add(new BotCommand("/mydata", "get my data"));
        listOfCommands.add(new BotCommand("/deletedata", "delete my data"));
        listOfCommands.add(new BotCommand("/help", "info  how to use this bot"));
        listOfCommands.add(new BotCommand("/settings", "set your preferences"));
        try {
            this.execute(new SetMyCommands(listOfCommands, new BotCommandScopeDefault(), null));
        }catch (TelegramApiException e){
            log.error("error setting bots command List: " + e.getMessage());
        }
        answerContains = List.of(
                questions[0].getAnswer(),
                questions[1].getAnswer(),
                questions[2].getAnswer(),
                questions[3].getAnswer(),
                questions[4].getAnswer(),
                questions[5].getAnswer(),
                questions[6].getAnswer(),
                questions[7].getAnswer(),
                questions[8].getAnswer(),
                questions[9].getAnswer()


        );
    }



    List<String> answerContains;
    @Autowired
    private UserRepository userRepository;
    private AbstractQuestion[] getQuestions() {
        final AbstractQuestion[] questions;


        questions = new AbstractQuestion[10];
        questions[0]=new Sag1();
        questions[1]= new sag2();
        questions[2]= new sag3();
        questions[3]= new sag4();
        questions[4]= new sag5();
        questions[5]= new sag6();
        questions[6]= new sag7();
        questions[7]= new sag8();
        questions[8]= new sag9();
        questions[9]= new sag10();


        return questions;
    }
    public String getBotUsername() {
        return config.getBotName();
    }
    @Override
    public String getBotToken() {
        return config.getToken();
    }
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()){
            Message msg = update.getMessage();
            String msgText = msg.getText();
            Long chatId = msg.getChatId();
            String firstName = msg.getFrom().getFirstName();
            String lastName = msg.getFrom().getLastName();
            User user = checkUser(chatId);

            int getQwe = user.getCurrentQuestion();
            switch (msgText){
                case "/start":
                    user = registerUser(msg);
                    start(chatId, user.getFirstName());
                    quizStart=false;
                    break;
                case "/question":
                    getQuestion(chatId,  getQwe);
                    quizStart =true;
                    break;
                case "/setNull":
                    setOfNull(chatId, true);
                    quizStart=false;
                    break;
                case "/mydata":
                    myData(chatId);
                    quizStart=false;
                    break;
                case "/deletedata":
                    deleteData(chatId);
                    quizStart=false;
                    break;
                case "/resetAllData":
                    resetAllData(chatId);
                    buildMSG(chatId, HELP_TEXT);
                    quizStart=false;
                    break;
                case "/help":
                    buildMSG(chatId, HELP_TEXT);
                    quizStart=false;
                    break;
                default:
                    if (quizStart) {
                        checking(msgText, getQwe, chatId, getQwe, msg, getQwe);
                    }else {
                        buildMSG(chatId, "Такой команды не существует, нажмите /help чтоб узнать все команды");

                    }
                    break;
            }
        }
    }
    private void checking(String msgText, int countQuestion, Long chatId, int countAnswer, Message msg, int currentQuestion) {
        User user  =checkUser(chatId);
         if (questions[countQuestion].checkAnswer(msgText)){
            buildMSG(chatId, "Правильно");
            userPlusBalls(msg.getChatId(), questions[countQuestion].getPlusBall());
            userUpdateQuestion(msg.getChatId(), ++countQuestion);
            getAnswer(chatId, countAnswer);
            try {
                buildMSG(chatId, "Вы получили за этот Вопрос Балл " + questions[countQuestion].getPlusBall());
            }catch (ArrayIndexOutOfBoundsException e){
                //buildMSG(chatId, "Вы выиграли");
            }
            getQuestion(chatId, countQuestion);
        }else if(!questions[countQuestion].checkAnswer(msgText)){
            buildMSG(chatId, "Не Правильно!!! Или такой команды нет ");
            minusCountFalse(chatId, 1);
            userPlusBalls(msg.getChatId(), -questions[countQuestion].getMinusBall());
            buildMSG(chatId, "У вас отнялось Балл "+ questions[countQuestion].getMinusBall());
            buildMSG(chatId, "У вас осталось " + user.getCountFalse() + " попытки");
            getQuestion(chatId, countQuestion);
            userUpdateQuestion(msg.getChatId(), currentQuestion);
        } if (user.getCountFalse() == 0){
            minusLive(chatId, 1);
            userUpdateCountFalse(chatId, 1);

        }if (user.getLive()<=0){
            buildMSG(chatId, "Game Over");
            setOfNull(chatId, false);
            quizStart=false;
            buildMSG(chatId, "Вы проиграли!!! Чтоб начать всё заново нажмите /resetAllData ");
            try {
                userUpdateQuestion(chatId, questions.length + 1);
            }catch (ArrayIndexOutOfBoundsException e){
            }

        }
    }
    private User checkUser(Long chatId) {
        User user = new User();
        if (userRepository.findById(chatId).isPresent()){
            user = userRepository.findById(chatId).get();
        }

        return user;
    }
    private void myData(Long chatId) {
        User user = checkUser(chatId);
        int questionNum = user.getCurrentQuestion()+1;
        buildMSG(chatId, "Твой Ник " + user.getUserName()+ "\n\n"+
                "Твои Баллы " + user.getBalls()+"\n\n"+
                "У тебя " + user.getLive() + " Жизни" +"\n\n"+
                "Ты Остановился на вопросе под номером " + questionNum

        );

    }
    private void deleteData(Long chatId) {
        //userUpdateBallsDelete(chatId, 0);
        userRepository.deleteAllById(Collections.singleton(chatId));
        buildMSG(chatId, "Вы Удалили свой Акаунт ");
    }
    private void resetAllData(Long chatId) {
        userUpdateBalls(chatId, 0);
        userUpdateQuestion(chatId, 0);
        userUpdateLive(chatId, 3);
        userUpdateCountFalse(chatId, 3);
    }
    private void setOfNull(Long chatId, boolean printMsg) {
        userUpdateQuestion(chatId, 0);
        if (printMsg) {
            buildMSG(chatId, "Ваша викторина переведена на наачало!!!, чтобы стартануть викторину нажмите /question\n\n"+
                    "Посмотрите ваш Результат /mydata");
        }
    }
    private void userUpdateCountFalse(Long chatId, int i) {
        Optional<User> user = userRepository.findById(chatId);
        user.get().setCountFalse(i);
        userRepository.save(user.get());
    }
    private void userUpdateLive(Long chatId, int i) {
        Optional<User> user = userRepository.findById(chatId);
        user.get().setLive(i);
        userRepository.save(user.get());
    }
    private void userUpdateBalls(Long chatId, int i) {
        Optional<User> user = userRepository.findById(chatId);
        user.get().setBalls(i);
        userRepository.save(user.get());
    }
    private void userUpdateQuestion(Long chatId, int current){
        Optional<User> user = userRepository.findById(chatId);
        user.get().setCurrentQuestion(current);
        userRepository.save(user.get());
    }
    private void minusCountFalse(Long chatId, int i) {
        Optional<User> user = userRepository.findById(chatId);
        user.get().setCountFalse(user.get().getCountFalse()-i);
        userRepository.save(user.get());
    }
    private void minusLive(Long chatId, int i) {

        Optional<User> user = userRepository.findById(chatId);
        user.get().setLive(user.get().getLive()-i);
        userRepository.save(user.get());
        buildMSG(chatId, "Вы потеряли жизнь, у вас осталось "+ user.get().getLive()+ " Жизни");
    }
    private void userPlusBalls(Long chatId, int i) {
        Optional<User> user = userRepository.findById(chatId);
        user.get().setBalls(user.get().getBalls()+i);
        userRepository.save(user.get());
    }
    private void getQuestion(Long chatId, int nextQuestion){
        try {
            buildMSG(chatId, questions[nextQuestion].getQuestion());
        }catch (ArrayIndexOutOfBoundsException e){
            buildMSG(chatId, "Вы решили все вопросы правильно!!! Поздравляем!!! чтобы начать заново, нажмите /setNull");
        }
    }
    private void getAnswer(Long chatId, int nextQuestion){
        buildMSG(chatId, questions[nextQuestion].getAnswer());
    }
    private User registerUser(Message msg) {

        if (userRepository.findById(msg.getChatId()).isEmpty()){
            var chatId = msg.getChatId();
            var chat = msg.getChat();

            User user = User.builder()
                    .chartId(chatId)
                    .registeredAt(new Timestamp(System.currentTimeMillis()))
                    .userName(chat.getUserName())
                    .firstName(chat.getFirstName())
                    .lastName(chat.getLastName())
                    .Balls(0)
                    .currentQuestion(0)
                    .live(3)
                    .countFalse(1)
                    .build();

            userRepository.save(user);
            log.info("Repository safe user: " + user.getUserName() );
            return user;
        }else {
            User user=new User();
            Optional<User> user1 = userRepository.findById(msg.getChatId());
            return user= user1.get();
        }

    }
    private void start(long chatId, String name){

        String answer = "Привет, " + name + " приятно познакомиться!!!\n\n Начни Викторину нажав /question";
        buildMSG(chatId, answer);
        log.info("Replied to User: " + name);
    }
    public void buildMSG(Long who, String what){
        SendMessage sendMSG = SendMessage.builder()
                .chatId(who.toString()) //Who are we sending a message to
                .text(what).build();    //Message content

        sendMSG(sendMSG);
    }
    private void sendMSG(SendMessage senMSG) {
        try {
            execute(senMSG);                        //Actually sending the message
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);      //Any error will be printed here
        }
    }

}
