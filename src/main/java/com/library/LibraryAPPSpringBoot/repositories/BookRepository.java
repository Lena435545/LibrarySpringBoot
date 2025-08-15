package com.library.LibraryAPPSpringBoot.repositories;

import com.library.LibraryAPPSpringBoot.models.Book;
import com.library.LibraryAPPSpringBoot.models.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findByOwner(Member owner);
    List<Book> findByNameStartingWithIgnoreCase(String title);
    List<Book> findByAuthorStartingWithIgnoreCase(String author);
}
