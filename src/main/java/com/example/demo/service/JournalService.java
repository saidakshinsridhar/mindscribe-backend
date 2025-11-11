package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.Journal;
import com.example.demo.repository.JournalRepository;

@Service
public class JournalService {

    private final JournalRepository repo;

    public JournalService(JournalRepository repo) {
        this.repo = repo;
    }

    public List<Journal> listAll() {
        return repo.findAll();
    }

    public Journal get(Long id) {
        return repo.findById(id).orElse(null);
    }

    public void save(Journal j) {
        repo.save(j);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
