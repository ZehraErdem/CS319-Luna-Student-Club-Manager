package luna.clubverse.backend.club.controller.response;

import lombok.Getter;
import luna.clubverse.backend.club.entity.Club;
import luna.clubverse.backend.user.entity.ClubDirector;
import luna.clubverse.backend.user.entity.FacultyAdvisor;
import luna.clubverse.backend.user.entity.Student;

import java.util.Set;

@Getter
public class ClubQueryResponse {

    private Long clubId;
    private String name;
    private String logo;
    private String description;


    public ClubQueryResponse(final Club club) {
        this.name = club.getName();
        this.logo = club.getLogo();
        this.description = club.getDescription();
        this.clubId = club.id();

    }
}
