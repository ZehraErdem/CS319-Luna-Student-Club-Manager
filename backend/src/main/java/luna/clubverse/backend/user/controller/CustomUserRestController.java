package luna.clubverse.backend.user.controller;

import luna.clubverse.backend.club.controller.response.ClubManagerCheckQueryResponse;
import luna.clubverse.backend.club.controller.response.ClubQueryResponse;
import luna.clubverse.backend.event.controller.response.EventListQueryResponse;
import luna.clubverse.backend.user.controller.response.ClubDirectorQueryResponse;
import luna.clubverse.backend.user.controller.response.FacultyAdvisorQueryResponse;
import luna.clubverse.backend.user.controller.response.StudentQueryResponse;
import luna.clubverse.backend.user.service.CustomUserService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomUserRestController {

    private final CustomUserService customUserService;

    public CustomUserRestController(CustomUserService customUserService) {
        this.customUserService = customUserService;
    }

    //GET MAPPING KOTU
    @GetMapping("/studentHomePage/{userId}")
    public List<EventListQueryResponse> getFutureEventsOfStudent(@PathVariable Long userId) {
        return customUserService.getFutureEventsOfStudent(userId);
    }

    @GetMapping("/getPastEventsOfStudent/{userId}")
    public List<EventListQueryResponse> getPastEventsOfStudent(@PathVariable Long userId) {
        return customUserService.getPastEventsOfStudent(userId);
    }

    @GetMapping("/getOnGoingEventsOfStudent/{userId}")
    public List<EventListQueryResponse> getOnGoingEventsOfStudent(@PathVariable Long userId) {
        return customUserService.getOnGoingEventsOfStudent(userId);
    }

    @CrossOrigin
    @GetMapping("/getClubsOfStudent/{studentId}")
    public List<ClubManagerCheckQueryResponse> getClubsOfUser(@PathVariable Long studentId ) {
        return customUserService.getClubsOfStudent(studentId);
    }

    @CrossOrigin
    @GetMapping("/getProfileOfStudent/{studentId}")
    public StudentQueryResponse getProfileOfUser(@PathVariable Long studentId ) {

        return new StudentQueryResponse(customUserService.getStudent(studentId));
    }

    @CrossOrigin
    @GetMapping("/getFacultyAdvisor/{id}")
    public FacultyAdvisorQueryResponse getProfileOfFacultyAdvisor(@PathVariable Long id ) {

        return new FacultyAdvisorQueryResponse(customUserService.getFacultyAdvisor(id));
    }


    @CrossOrigin
    @GetMapping("/getClubDirector/{id}")
    public ClubDirectorQueryResponse getProfileOfClubDirector(@PathVariable Long id ) {

        return new ClubDirectorQueryResponse(customUserService.getClubDirector(id));
    }

    @CrossOrigin
    @GetMapping("/getAllStudents")
    public List<StudentQueryResponse> getStudents() {
        return customUserService.getAllStudents();
    }
}
