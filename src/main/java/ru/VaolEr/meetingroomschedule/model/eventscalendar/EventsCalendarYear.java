package ru.VaolEr.meetingroomschedule.model.eventscalendar;

import ru.VaolEr.meetingroomschedule.dto.EventTo;
import ru.VaolEr.meetingroomschedule.model.Event;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for join calendar and events
 * @autor Valentin Eremizin
 * @version 0.1
 */
public class EventsCalendarYear {

    private List<EventsCalendarWeek> listOfWeeks = new ArrayList<>();

    /**
     * Constructor - create new EventsCalendarYear object for current system year
     */
    public EventsCalendarYear() {
        initYear();
    }

    /**
     * Constructor - create new EventsCalendarYear object for desired year
     * @param year - desired year
     */
    public EventsCalendarYear(Integer year) {
        initYear(year);
    }

    public void insertEvents(List<EventTo> events) {

        for (EventsCalendarWeek week : listOfWeeks) {
            week.insertEvents(events);
        }

    }

    public List<EventsCalendarWeek> getListOfWeeks() {
        return listOfWeeks;
    }


    /**
     * Function get Events Calendar Week by week number
     * @param weekNumber - week number for return (values from 0 to 52 are available)
     * @return EventsCalendarWeek
     */
    public EventsCalendarWeek getEventCalendarWeekByNumber(Integer weekNumber){
        if(weekNumber < 0 || weekNumber > 52) throw new IllegalArgumentException("weekNumber have to be in range [0-52], current " + weekNumber);
        else return listOfWeeks.get(weekNumber);
    }


    /**
     * Function for init list of weeks for current system year
     * @void generates listOfWeeks
     */
    private void initYear() {
        for (int i = 0; i < 53; i++) {
            this.listOfWeeks.add(new EventsCalendarWeek(i));
        }
    }

    /**
     * Function for init list of weeks for desired year
     * @param year - desired year
     * @void generates listOfWeeks
     */
    private void initYear(Integer year) {
        for (int i = 0; i < 53; i++) {
            this.listOfWeeks.add(new EventsCalendarWeek(year, i));
        }
    }
}
