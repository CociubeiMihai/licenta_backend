package com.example.hospital.utils;
import com.example.hospital.dtos.Event;
import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.Property;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class IcsParser {

    public List<Event> parseEvents(MultipartFile icsFile) {
        List<Event> events = new ArrayList<>();

        try (InputStream inputStream = icsFile.getInputStream()) {
            CalendarBuilder builder = new CalendarBuilder();
            Calendar calendar = builder.build(inputStream);

            for (Object component : calendar.getComponents()) {
                if (component instanceof Component) {
                    Component eventComponent = (Component) component;

                    if ("VEVENT".equals(eventComponent.getName())) {
                        Event event = new Event();

                        Property summary = eventComponent.getProperty("SUMMARY");
                        if (summary != null) {
                            event.setSummary(summary.getValue());
                        }

                        Property startDate = eventComponent.getProperty("DTSTART");
                        if (startDate != null) {
                            event.setStartDate(convertToDate(startDate.getValue()));
                        }

                        Property endDate = eventComponent.getProperty("DTEND");
                        if (endDate != null) {
                            event.setEndDate(convertToDate(endDate.getValue()));
                        }

                        events.add(event);
                    }
                }
            }
        } catch (IOException | ParserException e) {
            // Handle any errors that occur during parsing
            e.printStackTrace();
        }

        return events;
    }

    public static Date convertToDate(String dateString){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss'Z'");
        LocalDateTime localDateTime = LocalDateTime.parse(dateString, formatter);
        return Date.from(localDateTime.toInstant(ZoneOffset.UTC));
    }

}
