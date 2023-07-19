package sg.edu.nus.iss.paf_revision_boardgame.controller;

import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import sg.edu.nus.iss.paf_revision_boardgame.model.Comment;
import sg.edu.nus.iss.paf_revision_boardgame.service.GameService;

@Controller
@RequestMapping(path = "/api")
public class GameController {
    
    private GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping(path = "/boardgames")
    public ModelAndView getBoardGamesByName(
            @RequestParam String name, 
            @RequestParam(defaultValue = "10") int limit, 
            @RequestParam(defaultValue = "0") int offset) {

        System.out.println("\n\n" + "name >>>> " + name + "\n\n");
        System.out.println("\n\n" + "limit >>>> " + limit + "\n\n");
        System.out.println("\n\n" + "offset >>>> " + offset + "\n\n");

        ModelAndView mav = new ModelAndView();
        mav.setViewName("results");
        mav.addObject("games", gameService.findGameByName(name, limit, offset));
        mav.setStatus(HttpStatusCode.valueOf(200));

        return mav;
    }

    @GetMapping(path = "/boardgame/{id}")
    public ModelAndView getBoardGameById(@PathVariable int id) {
        
        System.out.println("\n\n" + "id >>>> " + id + "\n\n");

        ModelAndView mav = new ModelAndView();
        mav.setViewName("comments");
        mav.addObject("game", gameService.findGameById(id));
        mav.addObject("comments", gameService.findCommentsById(id));
        mav.addObject("newComment", new Comment());
        mav.setStatus(HttpStatusCode.valueOf(200));

        return mav;
    }

    @PostMapping(path = "/boardgame/{id}")
    public ModelAndView postComment(@PathVariable int id, Comment comment) {
        
        ModelAndView mav = new ModelAndView();
        
        gameService.insertComment(comment, id);

        mav.setViewName("comments");
        mav.addObject("game", gameService.findGameById(id));
        mav.addObject("comments", gameService.findCommentsById(id));
        mav.addObject("newComment", new Comment());
        mav.setStatus(HttpStatusCode.valueOf(201));

        return mav;
    }
}
