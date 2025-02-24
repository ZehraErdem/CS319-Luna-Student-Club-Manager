package luna.clubverse.backend.club.controller;


import luna.clubverse.backend.club.controller.request.AddClubRequest;
import luna.clubverse.backend.club.controller.request.ChangeDescriptionRequest;
import luna.clubverse.backend.club.controller.request.UpdateClubRequest;
import luna.clubverse.backend.club.controller.request.UploadPhotoRequest;
import luna.clubverse.backend.club.controller.response.*;
import luna.clubverse.backend.club.entity.Club;
import luna.clubverse.backend.club.service.ClubService;
import luna.clubverse.backend.common.MessageResponse;
import luna.clubverse.backend.common.MessageType;
import luna.clubverse.backend.event.controller.response.EventListQueryResponse;
import luna.clubverse.backend.user.service.AuthenticationService;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/club")
public class ClubRestController {

    private final ClubService clubService;
    private final AuthenticationService authenticationService;

    public ClubRestController(ClubService clubService, AuthenticationService authenticationService) { this.clubService = clubService;
        this.authenticationService = authenticationService;
    }

    /*
    @CrossOrigin
    @PostMapping("/add")
    public String addClub(@RequestBody @Valid final AddClubRequest addClubRequest) {
        clubService.addClub(addClubRequest.toClub());
        return "success"; // return type will be changed, except from get requests, there will be same type of response
    }
     */

    /*
    @CrossOrigin
    @PutMapping("/update/{id}")
    public String updateClub(@PathVariable Long id, @RequestBody @Valid final UpdateClubRequest updateClubRequest) {
        clubService.updateClub(id,updateClubRequest.toClub());
        return "success"; // return type will be changed, except from get requests, there will be same type of response
    }

     */

    @CrossOrigin
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/delete/{clubId}")
    public MessageResponse deleteClub(@PathVariable Long clubId) {
        return clubService.deleteClub(clubId);
    }

    @CrossOrigin
    @GetMapping("/get/{id}")
    public ClubQueryResponse getClub(@PathVariable Long id) {
        return new ClubQueryResponse(clubService.getClub(id));
    }

    @CrossOrigin
    @GetMapping("/getForStudent/{clubId}/{studentId}")
    public ClubQueryResponseForStudent getClubForStudent(@PathVariable Long clubId, @PathVariable Long studentId) {
        return clubService.getClubForStudent(clubId,studentId);
    }

    @CrossOrigin
    @GetMapping("/getWithPermissions/{clubId}/{studentId}")
    public ClubManagerClubQueryResponse getClubWithPermissions(@PathVariable Long clubId, @PathVariable Long studentId) {

        return clubService.getClubWithPermissions(clubId,studentId);
    }

    @CrossOrigin
    @GetMapping("/getEvents/{id}")
    public List<EventListQueryResponse> getClubEvents(@PathVariable Long id) {
        return clubService.getEventsOfClub(id);
    }

    @CrossOrigin
    @PreAuthorize("hasAuthority('STUDENT')")
    @PutMapping("/{clubId}/applyToClub/{studentId}")
    public String applyToClub(@PathVariable Long clubId,@PathVariable Long studentId ) {
        clubService.applyToClub(clubId,studentId);
        return "success";
    }

    @CrossOrigin
    //@PreAuthorize("hasAuthority('STUDENT')")
    @PutMapping("/{clubId}/approveStudent/{studentId}")
    public MessageResponse approveStudent(@PathVariable Long clubId,@PathVariable Long studentId ) {
        return clubService.approveStudent(clubId,studentId);
    }

    @CrossOrigin
    //@PreAuthorize("hasAuthority('STUDENT')")
    @PutMapping("/{clubId}/disapproveStudent/{studentId}")
    public MessageResponse disapproveStudent(@PathVariable Long clubId,@PathVariable Long studentId ) {
        return  clubService.disapproveStudent(clubId,studentId);
    }

    @CrossOrigin
    @PreAuthorize("hasAuthority('STUDENT')")
    @PutMapping("/{clubId}/directApplicationToClub/{studentId}")
    public MessageResponse directApplicationToClub(@PathVariable Long clubId,@PathVariable Long studentId ) {
        return clubService.directApplicationToClub(clubId,studentId);
    }

    @CrossOrigin
    @PreAuthorize("hasAuthority('STUDENT')")
    @PutMapping("/{clubId}/cancelMembershipToClub/{studentId}")
    public MessageResponse cancelMembershipToClub(@PathVariable Long clubId,@PathVariable Long studentId ) {
        clubService.cancelMembership(clubId,studentId);
        return new MessageResponse(MessageType.SUCCESS,"success");
    }

    @CrossOrigin
    @PutMapping("/{clubId}/cancelMembershipForManagerToClub/{studentId}")
    public MessageResponse cancelMembershipForManagerToClub(@PathVariable Long clubId,@PathVariable Long studentId ) {
        clubService.cancelMembershipForManager(clubId,studentId);
        return new MessageResponse(MessageType.SUCCESS,"success");
    }

    @CrossOrigin
    @PreAuthorize("@authorizationLuna.authorize(authentication,'REVIEW_MEMBER_APPLICATION', #clubId)")
    @PutMapping("/{clubId}/approveAppliedStudent/{studentId}")
    public String approveAppliedStudent(@PathVariable Long clubId,@PathVariable Long studentId ) {
        clubService.removeFromAppliedStudent(clubId,studentId, true);
        return "success";
    }

    @CrossOrigin
    @PreAuthorize("@authorizationLuna.authorize(authentication,'REVIEW_MEMBER_APPLICATION', #clubId)")
    @PutMapping("/{clubId}/disapproveAppliedStudent/{studentId}")
    public String disapproveFromAppliedStudent(@PathVariable Long clubId,@PathVariable Long studentId ) {
        clubService.removeFromAppliedStudent(clubId,studentId, false);
        return "success";
    }

    //Message Response için dön************************************
    @CrossOrigin
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/create-club")
    public MessageResponse createClub(@Valid @RequestBody AddClubRequest request) {
        Club club = clubService.addClub(request.toClub() );
        MessageResponse responseFromAccount = authenticationService.createClubDirectorAccount(request.toClubDirector(), club);
        authenticationService.sendClubRequestToAdvisor(request.getAdvisorMail());
        authenticationService.createFacultyAdvisorAccount(request.toFacultyAdvisor(), club);
        return responseFromAccount;
        //return new MessageResponse(MessageType.SUCCESS, "Club is created successfully");
    }


    @CrossOrigin
    @GetMapping("/getAllClubs")
    public List<ClubQueryResponse> getAllClubs( ) {
        return clubService.getAllClub();
    }

    @CrossOrigin
    @GetMapping("/get_club_list")
    public List<ClubListQueryResponse> getClubList(){
       return clubService.getClubList();
    }

    @CrossOrigin
    @PutMapping("/uploadPhotoForClubLogo/{clubId}")
    //@PreAuthorize("@authorizationLuna.authorize(authentication,'DIRECTOR', #clubId)")
    public MessageResponse uploadPhotoForClubLogo(@PathVariable Long clubId,@Valid @RequestBody UploadPhotoRequest request) {
        return clubService.changeLogo(clubId, request.getFile());
    }

    @CrossOrigin
    @PutMapping("/uploadPhotoForClubBackground/{clubId}")
    //@PreAuthorize("@authorizationLuna.authorize(authentication,'DIRECTOR', #clubId)")
    public MessageResponse uploadPhotoForClubBackground(@PathVariable Long clubId,@Valid @RequestBody UploadPhotoRequest request) {
        return clubService.changeBackgroundImage(clubId, request.getFile());
    }

    @CrossOrigin
    @GetMapping("/get_club_director_members/{clubId}")
    public List<MemberQueryresponse> getMemberList(@PathVariable Long clubId){
        return clubService.getMembers(clubId);
    }


    @CrossOrigin
    @PreAuthorize("@authorizationLuna.authorize(authentication,'FINANCE_MANAGEMENT', #clubId)" +
            "or @authorizationLuna.authorize(authentication,'ADVISOR', #clubId)")
    @GetMapping("/finance_table/{clubId}")
    public FinanceTableResponse getFinanceTable(@PathVariable Long clubId){
        return new FinanceTableResponse(clubService.getClub(clubId).getFinanceTable());
    }

    @CrossOrigin
    @PutMapping("/changeDescription/{clubId}")
    public MessageResponse changeDescription(@PathVariable Long clubId, @RequestBody @Valid final ChangeDescriptionRequest request){
        return clubService.changeDescription(clubId,request.getNewDescription());
    }

    @CrossOrigin
    @GetMapping("/candidates/{clubId}")
    public List<MemberCandidateQueryResponse> getCandidates(@PathVariable Long clubId) {
        return clubService.getCandidates(clubId);
    }



}
