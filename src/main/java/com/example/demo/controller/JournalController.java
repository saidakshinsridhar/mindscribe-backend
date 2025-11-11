package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Journal;
import com.example.demo.repository.JournalRepository;

@RestController
@RequestMapping("/journals")
@CrossOrigin(origins = "*") // allows frontend later
public class JournalController {

    @Autowired
    private JournalRepository journalRepository;

    // ✅ CREATE
    @PostMapping
    public Journal createJournal(@RequestBody Journal journal) {
        return journalRepository.save(journal);
    }

    // ✅ READ all
    @GetMapping
    public List<Journal> getAllJournals() {
        return journalRepository.findAll();
    }

    // ✅ READ one
    @GetMapping("/{id}")
    public Optional<Journal> getJournalById(@PathVariable Long id) {
        return journalRepository.findById(id);
    }

    // ✅ UPDATE
    @PutMapping("/{id}")
    public Journal updateJournal(@PathVariable Long id, @RequestBody Journal updatedJournal) {
        return journalRepository.findById(id)
                .map(journal -> {
                    journal.setTitle(updatedJournal.getTitle());
                    journal.setContent(updatedJournal.getContent());
                    journal.setDate(updatedJournal.getDate());
                    return journalRepository.save(journal);
                })
                .orElseThrow(() -> new RuntimeException("Journal not found with id " + id));
    }


    // ✅ DELETE
    @DeleteMapping("/{id}")
    public void deleteJournal(@PathVariable Long id) {
        journalRepository.deleteById(id);
    }
}
