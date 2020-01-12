package com.springtutorial.controller;

import com.springtutorial.service.GameService;
import com.springtutorial.util.AttributeNames;
import com.springtutorial.util.GameMappings;
import com.springtutorial.util.ViewNames;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.swing.text.View;

@Slf4j
@Controller

public class GameController {

    //==Fields==
    private final GameService gameService;

    //==CONSTRUCTOR==
    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    //==request Methods ==
    @GetMapping(GameMappings.PLAY)
    public String play(Model model)
    {
        model.addAttribute(AttributeNames.MAIN_MESSAGE,gameService.getMainMessage());
        model.addAttribute(AttributeNames.RESULT_MESSAGE,gameService.getResultMessage());
        log.info("model= {}",model);

        if(gameService.isGameOver()){
            return ViewNames.GAME_OVER;
        }

        return ViewNames.PLAY;
    }
    @PostMapping(GameMappings.PLAY)
    public String processMessage(@RequestParam int guess)
    {
        log.info("guess= {}",guess);
        gameService.checkGuess(guess);
        return GameMappings.REDIRECT_PLAY;

    }

    @GetMapping(GameMappings.RESTART)
    public String restart(){
        gameService.reset();
        return GameMappings.REDIRECT_PLAY;

    }
}
