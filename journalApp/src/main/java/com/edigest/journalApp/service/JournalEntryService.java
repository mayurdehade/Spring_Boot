package com.edigest.journalApp.service;

import com.edigest.journalApp.entity.JournalEntry;
import com.edigest.journalApp.entity.User;
import com.edigest.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalRepository;

    @Autowired
    private UserService userService;

    public void saveEntry(JournalEntry journalEntry, String username) {
        //use predefine method from MongoRepository
        User user = userService.findByUserName(username);
        journalEntry.setDate(LocalDateTime.now());
        JournalEntry saved = journalRepository.save(journalEntry);
        user.getJournalEntries().add(saved);
        userService.saveUser(user);
    }

    public void saveEntry(JournalEntry journalEntry) {
        journalEntry.setDate(LocalDateTime.now());
        journalRepository.save(journalEntry);
    }

    public List<JournalEntry> getAllEntries(){
        return journalRepository.findAll();
    }

    public Optional<JournalEntry> getEntryById(ObjectId id) {
        return journalRepository.findById(id);
    }

    public void deleteById(ObjectId id, String username) {
        User user = userService.findByUserName(username);
        user.getJournalEntries().removeIf(x -> x.getId().equals(id));
        userService.saveUser(user);
        journalRepository.deleteById(id);
    }

}
