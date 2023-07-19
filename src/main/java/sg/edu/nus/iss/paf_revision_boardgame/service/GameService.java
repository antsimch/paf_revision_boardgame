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
        return gameRepo.findGameByName(name, limit, offset);
    }

    public Game findGameById(int id) {
        List<Game> games = gameRepo.findGameById(id);
        return games.get(0);
    }

    public List<Comment> findCommentsById(int id) {
        return commentRepo.findCommentsById(id);
    }

    public int insertComment(Comment comment, int id) {
        return commentRepo.insertComment(comment, id);
    }
}
