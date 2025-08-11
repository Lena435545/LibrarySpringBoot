package com.library.LibraryAPPSpringBoot.controllers.repositories;

import com.library.LibraryAPPSpringBoot.controllers.models.Book;
import com.library.LibraryAPPSpringBoot.controllers.models.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findByOwner(Member owner);
    List<Book> findByNameStartingWith(String title);
}
