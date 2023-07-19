package sg.edu.nus.iss.paf_revision_boardgame.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Game {
    
    private Integer gid;

    private String name;

    private Integer year;

    private Integer ranking;

    private Integer usersRated;

    private String url;

    private String image;
}
