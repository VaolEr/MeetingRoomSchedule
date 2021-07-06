package ru.VaolEr.meetingroomschedule.model.eventscalendar;

import ru.VaolEr.meetingroomschedule.dto.EventTo;

import java.util.*;
/**
 * @Author VaolEr
 * This class describes calendar week with its events.
 * Each event is sorted by week day: monday, tuesday e.t.c.
 * Each event is sorted by day hour: Map<hour, list_of_this_hour_events>.
 * Each event is sorted by week day and day hour: Map<hour, array_of_this_hour_events_on_week_day_position>.
 */
public class EventsCalendarWeek {

    Integer weekNumber;
    private Date sunday;
    private Date monday;
    private Date tuesday;
    private Date wednesday;
    private Date thursday;
    private Date friday;
    private Date saturday;

    List<Date> weekDates = new ArrayList<>();

    /**
     * Each event is sorted by day hour: Map<hour, list_of_this_hour_events>
     */
    LinkedHashMap<Integer, ArrayList<EventTo>> eventsPerHours = new LinkedHashMap<>();

    /**
     * Each event is sorted by week day and day hour: Map<hour, array_of_this_hour_events_on_week_day_position>.
     * Maximum count of events per hour in one week day is 2.
     */
    LinkedHashMap<Integer, EventTo[]> perHourEvents = new LinkedHashMap<>();

    private final List<EventTo> mondayEvents = new ArrayList<>();
    private final List<EventTo> tuesdayEvents = new ArrayList<>();
    private final List<EventTo> wednesdayEvents = new ArrayList<>();
    private final List<EventTo> thursdayEvents = new ArrayList<>();;
    private final List<EventTo> fridayEvents = new ArrayList<>();;
    private final List<EventTo> saturdayEvents = new ArrayList<>();;

    /**
     * This method prepare week dates based on week in the year.
     * @param weekNumber - number of week in the year
     * Result of work is weekDates list with dates for weekNumber week.
     */
    public EventsCalendarWeek(Integer weekNumber){
        this.weekNumber = weekNumber;
        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.WEEK_OF_YEAR, weekNumber);

        for (int i = 2; i < 8; i++){
            calendar.set(Calendar.DAY_OF_WEEK, i);
            Date date = calendar.getTime();
            weekDates.add(date);
        }
    }

    /**
     * This method prepare week dates based on week in the year for desired year.
     * @param year - desired year
     * @param weekNumber - number of week in the year
     * Result of work is weekDates list with dates for weekNumber week for desired year.
     */
    public EventsCalendarWeek(Integer year, Integer weekNumber){
        this.weekNumber = weekNumber;
        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.WEEK_OF_YEAR, weekNumber);

        for (int i = 2; i < 8; i++){
            calendar.set(Calendar.DAY_OF_WEEK, i);
            Date date = calendar.getTime();
            weekDates.add(date);
        }
    }

    public List<EventTo> getMondayEvents() {
        return mondayEvents;
    }

    public List<EventTo> getTuesdayEvents() {
        return tuesdayEvents;
    }

    public List<EventTo> getWednesdayEvents() {
        return wednesdayEvents;
    }

    public List<EventTo> getThursdayEvents() {
        return thursdayEvents;
    }

    public List<EventTo> getFridayEvents() {
        return fridayEvents;
    }

    public List<EventTo> getSaturdayEvents() {
        return saturdayEvents;
    }

    public List<String> getWeekDates() {
        List<String> weekDatesAsString = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        for(int i = 0; i < weekDates.size(); i++){
            calendar.setTime(weekDates.get(i));
            weekDatesAsString.add(String.format("%d-%d-%d", calendar.get(Calendar.DATE), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.YEAR)));
        }
        return weekDatesAsString;
    }

    public LinkedHashMap<Integer, ArrayList<EventTo>> getEventsPerHours() {
        return eventsPerHours;
    }

    /**
     * This method returns events sorted by hour and stored int array corresponding to its week day.
     * @return LinkedHashMap<Integer, EventTo[]> perHourEvents
     * Integer is number of day hour: 0 - 24;
     * Example: EventTo[] = {event1, event2, ... , event12} represent next:
     * event1 - event in monday before half of hour, f.e: time = 10:25, 12:30, 18:15.
     * event2 - event in monday after half of hour, f.e: time = 10:35, 12:31, 18:45.
     */
    public LinkedHashMap<Integer, EventTo[]> getPerHourEvents() {
        return perHourEvents;
    }

    /**
     * This method insert data to dayEvents lists, and perHourEvents maps
     * @param eventsList - list of total year events
     */
    public void insertEvents(List<EventTo> eventsList){
        for(EventTo eventTo:eventsList){
            Date eventDate = eventTo.getDate();
            Calendar eventCal = new GregorianCalendar();
            eventCal.setTime(eventDate);
            for(Date weekDate:weekDates)
            {
                Calendar wdateCal = new GregorianCalendar();
                wdateCal.setTime(weekDate);
                if(wdateCal.get(Calendar.DAY_OF_YEAR) == eventCal.get(Calendar.DAY_OF_YEAR)){

                    switch (eventCal.get(Calendar.DAY_OF_WEEK)) {
                        case 2: mondayEvents.add(eventTo); break;
                        case 3: tuesdayEvents.add(eventTo); break;
                        case 4: wednesdayEvents.add(eventTo); break;
                        case 5: thursdayEvents.add(eventTo); break;
                        case 6: fridayEvents.add(eventTo); break;
                        case 7: saturdayEvents.add(eventTo); break;
                    }
                }
            }
        }
        initEventsPerHoursMap();
        initPerHourEventsMap();
    }

    private void initEventsPerHoursMap(){
        for(int i = 0; i < 25; i++){
            ArrayList<EventTo> arrayList = new ArrayList<>();
            eventsPerHours.put(i,arrayList);
        }
        getDayEventPerHourMap(mondayEvents);
        getDayEventPerHourMap(tuesdayEvents);
        getDayEventPerHourMap(wednesdayEvents);
        getDayEventPerHourMap(thursdayEvents);
        getDayEventPerHourMap(fridayEvents);
        getDayEventPerHourMap(saturdayEvents);
    }
    private void getDayEventPerHourMap(List<EventTo> eventsPerDay){

        eventsPerDay.forEach(eventTo -> {
            eventsPerHours.get(eventTo.getHourOfDay()).add(eventTo);
        });
    }

    /**
     * This method initialize perHourEvents map.
     *
     */
    private void initPerHourEventsMap(){
        for(int i = 0; i < 25; i++){
            EventTo[] eventTos = new EventTo[12];
            EventTo event = new EventTo();
            for(int j = 0; j<12; j++){
                eventTos[j] = event;
            }
            perHourEvents.put(i, eventTos);
        }
        preparePerHourEventsMap(mondayEvents);
        preparePerHourEventsMap(tuesdayEvents);
        preparePerHourEventsMap(wednesdayEvents);
        preparePerHourEventsMap(thursdayEvents);
        preparePerHourEventsMap(fridayEvents);
        preparePerHourEventsMap(saturdayEvents);
    }

    /**
     * This method prepare perHourEvents map.
     * @param eventsPerDayList - list of day events
     */
    private void preparePerHourEventsMap(List<EventTo> eventsPerDayList){
        for(EventTo event:eventsPerDayList){
            Integer dayOfWeek = event.getDayOfWeek();
            Integer hourOfDay = event.getHourOfDay();
            Integer minutesOfHour = event.getMinutesOfHour();
            switch (dayOfWeek){
                case 2:{
                    if(minutesOfHour > 30) perHourEvents.get(hourOfDay)[1] = event;
                    else perHourEvents.get(hourOfDay)[0] = event;
                } break;
                case 3:{
                    if(minutesOfHour > 30) perHourEvents.get(hourOfDay)[3] = event;
                    else perHourEvents.get(hourOfDay)[2] = event;
                } break;
                case 4:{
                    if(minutesOfHour > 30) perHourEvents.get(hourOfDay)[5] = event;
                    else perHourEvents.get(hourOfDay)[4] = event;
                } break;
                case 5:{
                    if(minutesOfHour > 30) perHourEvents.get(hourOfDay)[7] = event;
                    else perHourEvents.get(hourOfDay)[6] = event;
                } break;
                case 6:{
                    if(minutesOfHour > 30) perHourEvents.get(hourOfDay)[9] = event;
                    else perHourEvents.get(hourOfDay)[8] = event;
                } break;
                case 7:{
                    if(minutesOfHour > 30) perHourEvents.get(hourOfDay)[11] = event;
                    else perHourEvents.get(hourOfDay)[10] = event;
                } break;
            }
        }
    }
}
