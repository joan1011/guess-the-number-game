package com.springtutorial;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
@Component
public class MessageGeneratorImpl implements MessageGenerator {
    //==Constants==//
    private static final Logger log = LoggerFactory.getLogger(MessageGeneratorImpl.class);

    private final Game game;

    //==Constructors==


    public MessageGeneratorImpl(Game game) {
        this.game = game;
    }

    //==init method ==//
    @PostConstruct
    public void init(){
        log.info("game = {}",game);
    }


    @Override
    public String getMainMessage() {
        return "Number is between" + game.getSmallest() + " and " + game.getBiggest()+ "can you guess it";
    }

    @Override
    public String getResultMessage() {
        if(game.isGamewon())
        {
            return "you guessed it! the number was " + game.getNumber();
        }
        else if (game.isGameLost())
        {
            return "You lost! The number was" + game.getNumber();
        }
        else  if(!game.isValidNumberRange())
        {
            return "Invalid Number Range!";
        }
        else if(game.getRemainingguesses() == game.getGuessCount())
        {
            return  "what is your first guess?";
        }
        else {
            String direction = "Lower";
            if(game.getGuess()<game.getNumber())
            {
                direction = "Higher";
            }
            return  direction + "! you have" + game.getRemainingguesses() + "guesses left";
        }

    }

}