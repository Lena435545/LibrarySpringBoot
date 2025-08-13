package com.library.LibraryAPPSpringBoot.controllers.services;

import com.library.LibraryAPPSpringBoot.controllers.models.Journal;
import com.library.LibraryAPPSpringBoot.controllers.models.Member;
import com.library.LibraryAPPSpringBoot.controllers.repositories.JournalRepository;
import com.library.LibraryAPPSpringBoot.controllers.utils.ImageUploadUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class JournalService {

    private final JournalRepository journalRepository;

    @Value("${upload.dir}")
    private String uploadDir;

    public JournalService(JournalRepository journalRepository) {
        this.journalRepository = journalRepository;
    }

    public List<Journal> findAll() {
        return journalRepository.findAll();
    }

    public Journal findById(int id) {
        Optional<Journal> foundJournal = journalRepository.findById(id);
        return foundJournal.orElse(null);
    }

    @Transactional
    public void save(Journal journal, MultipartFile file) {
        if (!file.isEmpty()) {
            saveImageWithUniqueName(journal, file);
        }
        journalRepository.save(journal);
    }

    @Transactional
    public void update(int id, Journal updatedJournal, MultipartFile file) {
        if (!file.isEmpty()) {
            saveImageWithUniqueName(updatedJournal, file);
        } else {
            Optional<Journal> existingJournal = journalRepository.findById(id);
            existingJournal.ifPresent(journal -> updatedJournal.setImagePath(journal.getImagePath()));
        }
        updatedJournal.setJournalId(id);
        journalRepository.save(updatedJournal);
    }

    @Transactional
    public void delete(int id) {
        journalRepository.deleteById(id);
    }

    @Transactional
    public Optional<Member> getJournalOwner(int id) {
        return journalRepository.findById(id).map(Journal::getOwner);
    }

    @Transactional
    public void release(int id) {
        journalRepository.findById(id).ifPresent(journal -> {
            journal.setOwner(null);
            journal.setTakenAt(null);
            journalRepository.save(journal);
        });
    }

    @Transactional
    public void assign(int id, Member selectedMember) {
        journalRepository.findById(id).ifPresent(journal -> {
            journal.setOwner(selectedMember);
            journal.setTakenAt(LocalDateTime.now());
            journalRepository.save(journal);
        });
    }

    private void saveImageWithUniqueName(Journal journal, MultipartFile file) {
        try {
            String fileName = ImageUploadUtil.saveImageWithUniqueName(file, uploadDir);
            journal.setImagePath("images/" + fileName);
        } catch (IOException e) {
            System.err.println("Error during image upload: " + e.getMessage());
        }
    }


}
