package sg.edu.nus.iss.paf_revision_boardgame.repository;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.paf_revision_boardgame.model.Game;

@Repository
public class GameRepo {
    
    private JdbcTemplate template;

    private static final String SQL_FIND_BY_NAME = """
            select * from game where name like ? limit ? offset ?
            """;

    private static final String SQL_FIND_BY_ID = """
            select * from game where gid = ?
            """;

    public GameRepo(JdbcTemplate template) {
        this.template = template;
    }

    public List<Game> findGameByName(String name, int limit, int offset) {

        String likeName = "%" + name + "%";

        return template.query(
                SQL_FIND_BY_NAME, 
                BeanPropertyRowMapper.newInstance(Game.class), 
                likeName, 
                limit, 
                offset);
    }

    public List<Game> findGameById(int id) {
        return template.query(
                SQL_FIND_BY_ID,
                BeanPropertyRowMapper.newInstance(Game.class),
                id
        );
    }
}
