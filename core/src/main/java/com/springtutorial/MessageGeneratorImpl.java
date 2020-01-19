package com.springtutorial;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
@Component
public class MessageGeneratorImpl implements MessageGenerator {
    //==Constants==//
    private static final String MAIN_MESSAGE = "game.main.message";
    private static final String WIN = "game.win";
    private static final String LOSE = "game.lose";
    private static final String INVALID_RANGE = "game.invalid.range";
    private static final String FIRST_GUESS = "game.first.guess";
    private static final String HIGHER = "game.higher";
    private static final String LOWER = "game.lower";
    private static final String REMAINING = "game.remaining";



    private static final Logger log = LoggerFactory.getLogger(MessageGeneratorImpl.class);

    private final Game game;
    private final MessageSource messageSource;

    //==Constructors==


    public MessageGeneratorImpl(Game game, MessageSource messageSource) {
        this.game = game;
        this.messageSource = messageSource;
    }

    //==init method ==//
    @PostConstruct
    public void init(){
        log.info("game = {}",game);
    }


    @Override
    public String getMainMessage() {
        return getMessage(MAIN_MESSAGE,game.getSmallest(),game.getBiggest());

    }

    @Override
    public String getResultMessage() {
        if(game.isGamewon()){
            return getMessage(WIN,game.getNumber());
        }
        else if (game.isGameLost()) {
            return getMessage(LOSE,game.getNumber());
        }
        else  if(!game.isValidNumberRange()) {
            return getMessage(INVALID_RANGE,game.isValidNumberRange());
        }
        else if(game.getRemainingguesses() == game.getGuessCount()){
            return getMessage(FIRST_GUESS);
        }
        else {
            String direction = getMessage(LOWER);
            if(game.getGuess()<game.getNumber()) {
                direction = getMessage(HIGHER);
            }
            return  getMessage(REMAINING,direction,game.getRemainingguesses());
        }

    }

    //private methods
    private String getMessage(String code,Object... args){
        return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    }
}