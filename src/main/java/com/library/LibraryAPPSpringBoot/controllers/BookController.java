package com.library.LibraryAPPSpringBoot.controllers;

import com.library.LibraryAPPSpringBoot.models.Book;
import com.library.LibraryAPPSpringBoot.models.Member;
import com.library.LibraryAPPSpringBoot.models.enums.BookSortField;
import com.library.LibraryAPPSpringBoot.services.BookService;
import com.library.LibraryAPPSpringBoot.services.MemberService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

import static org.springframework.data.domain.Sort.Direction;

@Controller
@RequestMapping("/books")
public class BookController {

    private final MemberService memberService;
    private final BookService bookService;

    public BookController(MemberService memberService, BookService bookService) {
        this.memberService = memberService;
        this.bookService = bookService;
    }

    @GetMapping
    public String index(Model model,
                        @RequestParam(value = "sortBy", defaultValue = "NAME") String sortBy,
                        @RequestParam(value = "dir", defaultValue = "ASC") String dir) {

        String sortProp = BookSortField.resolve(sortBy);
        Direction direction = "DESC".equalsIgnoreCase(dir) ? Direction.DESC : Direction.ASC;

        model.addAttribute("books", bookService.findAll(Sort.by(direction, sortProp)));
        model.addAttribute("currentSortBy", sortBy.toUpperCase());
        model.addAttribute("currentDir", direction.isAscending() ? "ASC" : "DESC");
        return ("books/index");
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model, @ModelAttribute("member") Member member) {
        model.addAttribute("book", bookService.findById(id));

        Optional<Member> bookOwner = bookService.getBookOwner(id);

        if (bookOwner.isPresent())
            model.addAttribute("owner", bookOwner.get());
        else
            model.addAttribute("members", memberService.findAll());

        return ("books/show");
    }

    @GetMapping("/new")
    public String create(@ModelAttribute("book") Book book) {
        return ("books/new");
    }

    @PostMapping("/new")
    public String save(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult,
                       @RequestParam("image") MultipartFile file) {
        if (bindingResult.hasErrors())
            return ("books/new");

        bookService.save(book, file);

        return ("redirect:/books");
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", bookService.findById(id));
        return ("books/edit");
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable("id") int id, @ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult, @RequestParam("image") MultipartFile file) {
        if (bindingResult.hasErrors())
            return ("books/edit");
        bookService.update(id, book, file);
        return ("redirect:/books");
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        bookService.delete(id);
        return ("redirect:/books");
    }

    @PatchMapping("/{id}/release")
    public String release(@PathVariable("id") int id) {
        bookService.release(id);
        return "redirect:/books/" + id;
    }

    @PatchMapping("/{id}/assign")
    public String assign(@PathVariable("id") int id, @ModelAttribute("member") Member selectedMember) {
        bookService.assign(id, selectedMember);
        return "redirect:/books/" + id;
    }
}
