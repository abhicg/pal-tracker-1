package io.pivotal.pal.tracker;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
public class InMemoryTimeEntryRepository implements TimeEntryRepository {

   private Map<Long,TimeEntry> timeEntries = new LinkedHashMap<>();
   private long id = 1L;


   public InMemoryTimeEntryRepository(){}

    @Override
    public TimeEntry create(TimeEntry timeEntry) {

        TimeEntry timeEntry_Created
                = new TimeEntry(id++,
                                timeEntry.getProjectId(),
                                timeEntry.getUserId(),
                                timeEntry.getDate(),
                                timeEntry.getHours());

        timeEntries.put(timeEntry_Created.getId(), timeEntry_Created);
        return timeEntry_Created;
    }

    @Override
    public TimeEntry find(Long id) {
        if(null != id)
        return timeEntries.get(id);
        else return null;
    }

    @Override
    public List<TimeEntry> list() {
        List<TimeEntry> lst = new ArrayList<>();
        for ( TimeEntry timeEntry : timeEntries.values()){
            lst.add(timeEntry);
        }

            return lst;
    }

    @Override
    public TimeEntry update(Long id, TimeEntry timeEntry) {

        if (null!= this.find(id)) {

            TimeEntry timeEntry_Updated
                    = new TimeEntry(id,
                    timeEntry.getProjectId(),
                    timeEntry.getUserId(),
                    timeEntry.getDate(),
                    timeEntry.getHours());

            timeEntries.put(timeEntry_Updated.getId(), timeEntry_Updated);

            return timeEntry_Updated;
        }
        else
            return null;
    }

    @Override
    public void delete(Long id) {
        if (null != timeEntries.get(id))
        timeEntries.remove(id);

    }
}
