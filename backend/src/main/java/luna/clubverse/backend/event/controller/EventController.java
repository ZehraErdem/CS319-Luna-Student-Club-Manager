package luna.clubverse.backend.event.controller;

import luna.clubverse.backend.event.controller.request.AddEventRequest;
import luna.clubverse.backend.event.service.EventService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/event")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @CrossOrigin
    @PostMapping("/add") // post yeni şey eklemek için yapılır
    @PreAuthorize("@authorizationDemo.authorize(authentication)")
    public String addEvent(@RequestBody AddEventRequest addEventRequest) {
        eventService.addEvent(addEventRequest.toEvent());
        return "success";
    }

    //put update etmek için kullanılır

    //delete silmek için kullanılır

    //get query işlmeleri için kullanılır


}

