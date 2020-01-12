package com.springtutorial;

public interface Game {
    int getNumber();

    int getGuess();

    void setGuess(int guess);

    int getSmallest();

    int getBiggest();

    int getRemainingguesses();

    int getGuessCount();

    void  Reset();

    void Check();

    boolean isValidNumberRange();

    boolean isGamewon();

    boolean isGameLost();

}
