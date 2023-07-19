package sg.edu.nus.iss.paf_revision_boardgame.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.paf_revision_boardgame.model.Comment;

@Repository
public class CommentRepo {
    
    private JdbcTemplate template;

    private static final String SQL_FIND_BY_GID = """
            select * from comment where gid = ? limit 5
            """;
    
    private static final String SQL_FIND_AVERAGE_RATING = """
            select avg(rating) from comment where gid = ?
            """;

    private static final String SQL_INSERT_COMMENT = """
            insert into comment (user, rating, c_text, gid) values (?, ?, ?, ?)
            """;

    public CommentRepo(JdbcTemplate template) {
        this.template = template;
    }

    public List<Comment> findCommentsById(int id) {
        return template.query(
                SQL_FIND_BY_GID,
                BeanPropertyRowMapper.newInstance(Comment.class),
                id
        );
    }

    public int insertComment(Comment comment, int id) {
        return template.update(
                SQL_INSERT_COMMENT, 
                comment.getUser(), 
                comment.getRating(), 
                comment.getCText(), 
                id);
    }

    public double findAverageRating(int id) {
        return template.queryForObject(
                SQL_FIND_AVERAGE_RATING,
                Double.class,
                id
        );
    }
}
