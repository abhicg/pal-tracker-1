package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.*;

public interface TimeEntryRepository {
    public TimeEntry create(TimeEntry timeEntry);
    public TimeEntry update(long id, TimeEntry timeEntry);
    public TimeEntry find(Long id);
    public List<TimeEntry> list();
    public void delete(Long id);

}