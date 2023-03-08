package com.example.akkar2.services;

import com.example.akkar2.entities.ExpertAppointment;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.*;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;

/** Service to perform API operations for Google Calendar API. */
@Service
public class GoogleCalendarService {
    private com.google.api.services.calendar.Calendar googleCalendar;

    public GoogleCalendarService(com.google.api.services.calendar.Calendar googleCalendar) {
        this.googleCalendar = googleCalendar;
    }

    /**
     * Returns metadata for a calendar.
     *
     * @param calendarId Calendar identifier
     * @return {@link Calendar}
     * @throws IOException when an error occurs in the HTTP request
     */
    public Calendar getMetaInfoForCalendar(@NonNull String calendarId) throws IOException {
        return googleCalendar.calendars().get(calendarId).execute();
    }

    /**
     * Returns events on the specified calendar.
     *
     * @param calendarId Calendar identifier
     * @return {@link Events}
     * @throws IOException when an error occurs in the HTTP request
     */
    public Events getEventsForCalendar(@NonNull String calendarId) throws IOException {
        return googleCalendar.events().list(calendarId).execute();
    }

    /**
     * Returns an event.
     *
     * @param calendarId Calendar identifier
     * @param eventId Event identifier
     * @return {@link Event}
     * @throws IOException when an error occurs in the HTTP request
     */
    public Event getEventInfoFromCalendar(@NonNull String calendarId, @NonNull String eventId)
            throws IOException {
        return googleCalendar.events().get(calendarId, eventId).execute();
    }



    /**
     * Returns metadata for a calendar.
     *
     * @param calendarId Calendar identifier
     * @return {@link Calendar}
     * @throws IOException when an error occurs in the HTTP request
     */
    public Event addEventManual(@NonNull String calendarId) throws IOException{
        Event event = new Event()
                .setSummary("Google I/O 2015")
                .setLocation("800 Howard St., San Francisco, CA 94103")
                .setDescription("A chance to hear more about Google's developer products.");

        DateTime startDateTime = new DateTime("2022-04-28T09:00:00-07:00");
        EventDateTime start = new EventDateTime()
                .setDateTime(startDateTime)
                .setTimeZone("America/Los_Angeles");
        event.setStart(start);

        DateTime endDateTime = new DateTime("2022-04-30T17:00:00-07:00");
        EventDateTime end = new EventDateTime()
                .setDateTime(endDateTime)
                .setTimeZone("America/Los_Angeles");
        event.setEnd(end);

        String[] recurrence = new String[] {"RRULE:FREQ=DAILY;COUNT=2"};
        event.setRecurrence(Arrays.asList(recurrence));

        /*EventAttendee[] attendees = new EventAttendee[] {
                new EventAttendee().setEmail("lpage@example.com"),
                new EventAttendee().setEmail("sbrin@example.com"),
        };
        event.setAttendees(Arrays.asList(attendees));*/

        EventReminder[] reminderOverrides = new EventReminder[] {
                new EventReminder().setMethod("email").setMinutes(24 * 60),
                new EventReminder().setMethod("popup").setMinutes(10),
        };
        Event.Reminders reminders = new Event.Reminders()
                .setUseDefault(false)
                .setOverrides(Arrays.asList(reminderOverrides));
        event.setReminders(reminders);

        calendarId = "ilyes.nakhli@esprit.tn";
        event = googleCalendar.events().insert(calendarId, event).execute();
        System.out.printf("Event created: %s\n", event.getHtmlLink());
        return event;

    }


    /**
     * Returns metadata for a calendar.
     *
     *
     * @return {@link Calendar}
     * @throws IOException when an error occurs in the HTTP request
     */
    public Event addEventToCalendar(@NonNull ExpertAppointment e ) throws IOException{
        Event event = new Event()
                .setSummary("hello")
                .setLocation("Tunisia")
                .setDescription("no Description");

        DateTime startDateTime = new DateTime(e.getStartTime());
        EventDateTime start = new EventDateTime()
                .setDateTime(startDateTime)
                .setTimeZone("America/Los_Angeles");
        event.setStart(start);

        DateTime endDateTime = new DateTime(e.getEndTime());
        EventDateTime end = new EventDateTime()
                .setDateTime(endDateTime)
                .setTimeZone("America/Los_Angeles");
        event.setEnd(end);

        String[] recurrence = new String[] {"RRULE:FREQ=DAILY;COUNT=2"};
        event.setRecurrence(Arrays.asList(recurrence));

        /*EventAttendee[] attendees = new EventAttendee[] {
                new EventAttendee().setEmail("lpage@example.com"),
                new EventAttendee().setEmail("sbrin@example.com"),
        };
        event.setAttendees(Arrays.asList(attendees));*/

        EventReminder[] reminderOverrides = new EventReminder[] {
                new EventReminder().setMethod("email").setMinutes(24 * 60),
                new EventReminder().setMethod("popup").setMinutes(10),
        };
        Event.Reminders reminders = new Event.Reminders()
                .setUseDefault(false)
                .setOverrides(Arrays.asList(reminderOverrides));
        event.setReminders(reminders);

        String calendarId = "ilyes.nakhli@esprit.tn";
        event = googleCalendar.events().insert(calendarId, event).execute();
        System.out.printf("Event created: %s\n", event.getHtmlLink());
        return event;

    }
}
