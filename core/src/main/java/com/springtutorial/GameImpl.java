package com.springtutorial;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Slf4j
@Getter
@Component
public class GameImpl implements Game{

    // == fields ==
@Getter(AccessLevel.NONE)
    private NumberGenerator numberGenerator;


    private final int guessCount;
    private int number;
    private  int guess;
    private  int smallest;
    private  int biggest;
    private int remainingGuesses;
    private boolean validNumberRange = true;


    //==Constructor==
    @Autowired
    public GameImpl(NumberGenerator numberGenerator,@GuessCount int guessCount) {
        this.numberGenerator = numberGenerator;
        this.guessCount = guessCount;
    }

    // == INIT ==
    @PostConstruct
    @Override
    public void Reset() {
        smallest = numberGenerator.getMinNumber();
        guess = numberGenerator.getMinNumber();
        remainingGuesses = guessCount;
        biggest = numberGenerator.getMaxNumber();
        number = numberGenerator.next();
        log.debug("the number is {}",number);

    }
    @PreDestroy
   public void preDestroy() {
        log.info("in Game preDestroy()");
   }

    // == Public methods==
   public void setNumberGenerator(NumberGenerator numberGenerator){
       this.numberGenerator = numberGenerator;
   }


    @Override
    public int getNumber() {
        return number;
    }

    @Override
    public int getGuess() {
        return guess;
    }

    @Override
    public void setGuess(int guess) {
        this.guess = guess;
    }

    @Override
    public int getSmallest() {
        return smallest;
    }

    @Override
    public int getBiggest() {
        return biggest;
    }

    @Override
    public int getRemainingguesses() {
        return remainingGuesses;
    }

    @Override
    public int getGuessCount() {
        return guessCount;
    }

    @Override
    public void Check() {
        checkValidNumberRange();
        if(validNumberRange){
            if(guess>number){
                biggest = guess -1;
            }
            if(guess<number){
                smallest = guess +1;
            }
        }
        remainingGuesses--;
    }

    @Override
    public boolean isValidNumberRange() {
        return validNumberRange;
    }

    @Override
    public boolean isGamewon() {
        return guess == number;
    }

    @Override
    public boolean isGameLost() {
        return !isGamewon()&& remainingGuesses <=0;
    }

    // == private methods ==
    private void checkValidNumberRange(){
        validNumberRange = (guess >= smallest)&&(guess <= biggest);
    }
}
