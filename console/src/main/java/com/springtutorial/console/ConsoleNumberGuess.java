package com.springtutorial.console;

import com.springtutorial.Game;
import com.springtutorial.MessageGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ConsoleNumberGuess  {

    //===constants==
    private static final Logger log = LoggerFactory.getLogger(ConsoleNumberGuess.class);

    // ==fields==

    private final Game game;

    private final MessageGenerator messageGenerator;

    // ==Constructor==

    public ConsoleNumberGuess(Game game, MessageGenerator messageGenerator) {
        this.game = game;
        this.messageGenerator = messageGenerator;
    }

    @EventListener(ContextRefreshedEvent.class)
    public void start() {
        log.info("start()---> Container ready for use.");

        Scanner scanner = new Scanner(System.in);
        while (true)
        {
            System.out.println(messageGenerator.getMainMessage());
            System.out.println(messageGenerator.getResultMessage());
            int guess = scanner.nextInt();
            scanner.nextLine();
            game.setGuess(guess);
            game.Check();
            if(game.isGamewon() || game.isGameLost()){
                System.out.println(messageGenerator.getResultMessage());
                System.out.println("Play again y/n");

                String playAgainString = scanner.nextLine().trim();
                if(!playAgainString.equalsIgnoreCase("y"))
                {
                    break;
                }
                game.Reset();
            }
        }
    }

}
