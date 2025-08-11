package com.library.LibraryAPPSpringBoot.controllers.repositories;

import com.library.LibraryAPPSpringBoot.controllers.models.Journal;
import com.library.LibraryAPPSpringBoot.controllers.models.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JournalRepository extends JpaRepository<Journal, Integer> {
    List<Journal> findByOwner(Member owner);
}
