package com.library.LibraryAPPSpringBoot.repositories;

import com.library.LibraryAPPSpringBoot.models.Film;
import com.library.LibraryAPPSpringBoot.models.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmRepository extends JpaRepository<Film, Integer> {
    List<Film> findByOwner(Member owner);
}
