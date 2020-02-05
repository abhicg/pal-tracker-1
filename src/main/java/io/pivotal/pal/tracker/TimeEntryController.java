package io.pivotal.pal.tracker;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.util.List;

@RestController
public class TimeEntryController {


    private TimeEntryRepository timeEntryRepository;
    @Autowired
    private TimeEntry timeEntry;

    private ResponseEntity responseEntity;

    @Autowired
    public TimeEntryController(TimeEntryRepository timeEntryRepository){
        this.timeEntryRepository = timeEntryRepository;
    }

    @PostMapping("/time-entries")
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEntry) {
        ResponseEntity<TimeEntry> respEntity = null;
        if (null != timeEntry)
            respEntity = new ResponseEntity<TimeEntry>(timeEntryRepository.create(timeEntry), HttpStatus.CREATED);
        return respEntity;
    }

    @GetMapping("/time-entries/{timeEntryId}")
    public ResponseEntity<TimeEntry> read(@PathVariable Long timeEntryId) {
        ResponseEntity<TimeEntry> respEntity = null;
        TimeEntry timeEntry = null;
        timeEntry =  timeEntryRepository.find(timeEntryId);
        if (null != timeEntry)
            respEntity = new ResponseEntity<TimeEntry>((timeEntry), HttpStatus.OK);
        else
            respEntity = new ResponseEntity<TimeEntry>(HttpStatus.NOT_FOUND);

        return respEntity;
    }

    @GetMapping("/time-entries")
    public ResponseEntity<List<TimeEntry>> list() {
        ResponseEntity<List<TimeEntry>> respEntity = new ResponseEntity<List<TimeEntry>>(timeEntryRepository.list(), HttpStatus.OK);
        return respEntity;
    }

    @PutMapping("/time-entries/{timeEntryId}")
    public ResponseEntity<TimeEntry> update(@PathVariable Long timeEntryId,@RequestBody TimeEntry timeEntry) {
        ResponseEntity<TimeEntry> respEntity = null;
        TimeEntry timeEntry1 = null;
        timeEntry1 =  timeEntryRepository.update(timeEntryId,timeEntry );
        if (null != timeEntry1)
            respEntity = new ResponseEntity<TimeEntry>((timeEntry1), HttpStatus.OK);
        else
            respEntity = new ResponseEntity<TimeEntry>(HttpStatus.NOT_FOUND);

        return respEntity;
    }

    @DeleteMapping("/time-entries/{timeEntryId}")
    public ResponseEntity<Void> delete(@PathVariable Long timeEntryId) {
        timeEntryRepository.delete(timeEntryId);
        ResponseEntity<Void> respEntity = new ResponseEntity<>( HttpStatus.NO_CONTENT);
        return respEntity;
    }
}
