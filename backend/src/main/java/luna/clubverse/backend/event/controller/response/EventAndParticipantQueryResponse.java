package luna.clubverse.backend.event.controller.response;

import luna.clubverse.backend.event.entity.Event;
import luna.clubverse.backend.event.enumuration.EventStatus;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class EventAndParticipantQueryResponse {

    private final String name;
    private final String club;
    private final String description;
    private final EventStatus eventStatus;
    private final int gePoint;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final LocalDate registrationDeadline;
    private final LocalDate reviewDeadline;
    private LocalTime startTime;
    private LocalTime endTime;
    private final int quota;
    private final int remainingQuota;
    private final boolean memberOnly;
    private final int totalPoint;
    private final int numberEvaluation;

    private final double amountOfMoney;
    private final String explanation;

    //location
    private final Boolean inBilkent;
    private final String descriptionLocation;

    private final List<ParticipantResponse> participants;

    public EventAndParticipantQueryResponse(final Event event) {
        this.name = event.getName();
        this.club = event.getClub().getName();
        this.description = event.getDescription();
        this.eventStatus = event.getEventStatus();
        this.gePoint = event.getGePoint();
        this.startDate = event.getStartDate();
        this.endDate = event.getEndDate();
        this.registrationDeadline = event.getRegistrationDeadline();
        this.reviewDeadline = event.getReviewDeadline();
        this.quota = event.getQuota();
        this.remainingQuota = event.getRemainingQuota();
        this.memberOnly = event.isMemberOnly();
        this.totalPoint = event.getTotalPoint();
        this.numberEvaluation = event.getNumberEvaluation();
        this.startTime = event.getStartTime();
        this.endTime = event.getEndTime();
        this.amountOfMoney = event.getFinanceData().amountOfMoney();
        this.explanation = event.getFinanceData().explanation();
        this.inBilkent = event.getLocation().inBilkent();
        this.descriptionLocation = event.getLocation().description();

        this.participants = event.getEnrolledStudents()
                .stream()
                .map(ParticipantResponse::new)
                .toList();


    }
}
