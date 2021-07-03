package ru.VaolEr.meetingroomschedule.model.eventscalendar;

import ru.VaolEr.meetingroomschedule.dto.EventTo;
import ru.VaolEr.meetingroomschedule.model.Event;

import java.util.*;

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

    LinkedHashMap<Integer, ArrayList<EventTo>> eventsPerHours = new LinkedHashMap<>();

    private List<EventTo> mondayEvents = new ArrayList<>();
    private List<EventTo> tuesdayEvents = new ArrayList<>();
    private List<EventTo> wednesdayEvents = new ArrayList<>();
    private List<EventTo> thursdayEvents = new ArrayList<>();;
    private List<EventTo> fridayEvents = new ArrayList<>();;
    private List<EventTo> saturdayEvents = new ArrayList<>();;

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

    public void insertEvents(List<EventTo> eventsList){
        for(EventTo eventTo:eventsList){
            Date eventDate = eventTo.getDate();
            Calendar eventCal = new GregorianCalendar();
            eventCal.setTime(eventTo.getDate());
            for(Date weekDate:weekDates)
            {
                Calendar wdateCal = new GregorianCalendar();
                wdateCal.setTime(weekDate);
                if(wdateCal.get(Calendar.DAY_OF_YEAR) == eventCal.get(Calendar.DAY_OF_YEAR)){
                    Calendar calendar = new GregorianCalendar();
                    calendar.setTime(eventDate);

                    switch (calendar.get(Calendar.DAY_OF_WEEK)) {
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
            //eventsPerHours.put(eventTo.getHourOfDay(),new ArrayList<>());
            eventsPerHours.get(eventTo.getHourOfDay()).add(eventTo);
        });
    }
}
