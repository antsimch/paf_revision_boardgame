package sg.edu.nus.iss.paf_revision_boardgame.service;

import java.util.List;

import org.springframework.stereotype.Service;

import sg.edu.nus.iss.paf_revision_boardgame.model.Comment;
import sg.edu.nus.iss.paf_revision_boardgame.model.Game;
import sg.edu.nus.iss.paf_revision_boardgame.repository.CommentRepo;
import sg.edu.nus.iss.paf_revision_boardgame.repository.GameRepo;

@Service
public class GameService {
    
    private GameRepo gameRepo;

    private CommentRepo commentRepo;

    public GameService(GameRepo gameRepo, CommentRepo commentRepo) {
        this.gameRepo = gameRepo;
        this.commentRepo = commentRepo;
    }

    public List<Game> findGameByName(String name, int limit, int offset) {
        
        List<Game> games = gameRepo.findGameByName(name, limit, offset);
        
        for (Game game : games)
            game.setAverageRating(findAverageRating(game.getGid()));

        return games;
    }

    public Game findGameById(int id) {
        Game game = gameRepo.findGameById(id).get(0);
        game.setAverageRating(findAverageRating(id));
        return game;
    }

    public List<Comment> findCommentsById(int id) {
        return commentRepo.findCommentsById(id);
    }

    public int insertComment(Comment comment, int id) {
        return commentRepo.insertComment(comment, id);
    }

    public Double findAverageRating(int id) {

        if (findCommentsById(id).isEmpty()) {
            return null;
        }

        return commentRepo.findAverageRating(id);
    }
}
