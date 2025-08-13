package com.library.LibraryAPPSpringBoot.controllers.services;

import com.library.LibraryAPPSpringBoot.controllers.models.Book;
import com.library.LibraryAPPSpringBoot.controllers.models.Film;
import com.library.LibraryAPPSpringBoot.controllers.models.Journal;
import com.library.LibraryAPPSpringBoot.controllers.models.Member;
import com.library.LibraryAPPSpringBoot.controllers.repositories.BookRepository;
import com.library.LibraryAPPSpringBoot.controllers.repositories.FilmRepository;
import com.library.LibraryAPPSpringBoot.controllers.repositories.JournalRepository;
import com.library.LibraryAPPSpringBoot.controllers.repositories.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;
    private final FilmRepository filmRepository;
    private final JournalRepository journalRepository;
    //Using seconds instead of days to allow real-time testing
    private static final int EXPIRATION_SECONDS = 20;

    public MemberService(MemberRepository memberRepository, BookRepository bookRepository, FilmRepository filmRepository, JournalRepository journalRepository) {
        this.memberRepository = memberRepository;
        this.bookRepository = bookRepository;
        this.filmRepository = filmRepository;
        this.journalRepository = journalRepository;
    }

    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    public Member findById(int id) {
        Optional<Member> foundMember = memberRepository.findById(id);
        return foundMember.orElse(null);
    }

    @Transactional
    public void save(Member member) {
        memberRepository.save(member);
    }

    @Transactional
    public void update(int id, Member updatedMember) {
        updatedMember.setMemberId(id);
        memberRepository.save(updatedMember);
    }

    @Transactional
    public void delete(int id) {
        memberRepository.deleteById(id);
    }

    public List<Book> findBooksByOwner(Member owner) {
        List<Book> books = bookRepository.findByOwner(owner);

        books.forEach(book ->
                book.setExpired(
                        book.getTakenAt() != null &&
                                Duration.between(book.getTakenAt(), LocalDateTime.now()).toSeconds() > EXPIRATION_SECONDS
                ));

        return books;
    }

    public List<Film> findFilmsByOwner(Member owner) {
        List<Film> films = filmRepository.findByOwner(owner);

        films.forEach(film ->
                film.setExpired(
                        film.getTakenAt() != null &&
                                Duration.between(film.getTakenAt(), LocalDateTime.now()).toSeconds()> EXPIRATION_SECONDS
                ));

        return films;
    }

    public List<Journal> findJournalsByOwner(Member owner) {
        List<Journal> journals = journalRepository.findByOwner(owner);

        journals.forEach(journal ->
                journal.setExpired(
                        journal.getTakenAt() != null &&
                                Duration.between(journal.getTakenAt(), LocalDateTime.now()).toSeconds() > EXPIRATION_SECONDS

                ));
        return journalRepository.findByOwner(owner);
    }
}
