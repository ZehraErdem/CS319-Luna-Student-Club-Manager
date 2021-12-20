package luna.clubverse.backend.club.controller;

import luna.clubverse.backend.club.entity.Club;
import luna.clubverse.backend.club.service.ClubService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ClubController {

    private final ClubService clubService;

    public ClubController(ClubService clubService) {
        this.clubService = clubService;
    }

    @RequestMapping("/app/club_home_page_{userType}/{clubId}")
    public String getClubHomePage( Model model,@PathVariable String userType, @PathVariable Long clubId) {
        Club club = clubService.getClub(clubId);
        model.addAttribute("club", club);
        final String url = "club_home_page_";
        //userType = userType.toLowerCase();
        return url + userType;
    }

    @RequestMapping("/app/open_all_clubs")
    public String openAllClubs() {
        return "all_club_list";
    }

    @RequestMapping("/app/open_clubs_student")
    public String openStudentClubs() {
        return "club_list_student";
    }
}
