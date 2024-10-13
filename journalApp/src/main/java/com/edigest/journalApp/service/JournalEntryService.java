package com.edigest.journalApp.service;

import com.edigest.journalApp.entity.JournalEntry;
import com.edigest.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalRepository;

    public void saveEntry(JournalEntry journalEntry) {
        //use predefine method from MongoRepository
        journalRepository.save(journalEntry);
    }

    public List<JournalEntry> getAllEntries(){
        return journalRepository.findAll();
    }

    public Optional<JournalEntry> getEntryById(ObjectId id) {
        return journalRepository.findById(id);
    }

    public void deleteById(ObjectId id) {
        journalRepository.deleteById(id);
    }

}
