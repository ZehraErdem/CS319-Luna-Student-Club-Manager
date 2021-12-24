package luna.clubverse.backend.club.controller.response;

import lombok.Getter;
import luna.clubverse.backend.club.entity.Club;
import luna.clubverse.backend.user.entity.Student;
import luna.clubverse.backend.user.entity.Title;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Getter
public class ClubManagerCheckQueryResponse extends ClubQueryResponse {
    private boolean manager;

    public ClubManagerCheckQueryResponse(final Club club, final Long userId) {
        super(club);
        this.manager = false;

        for (BoardMemberQueryResponse boardMember: this.getBoardMembers()){
           if(Objects.equals(userId, boardMember.getUserId())){
               manager=true;
               break;
           }
        }
    }


}
