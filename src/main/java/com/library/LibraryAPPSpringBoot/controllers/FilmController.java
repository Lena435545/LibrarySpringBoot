package com.library.LibraryAPPSpringBoot.controllers;

import com.library.LibraryAPPSpringBoot.models.Film;
import com.library.LibraryAPPSpringBoot.models.Member;
import com.library.LibraryAPPSpringBoot.repositories.MemberRepository;
import com.library.LibraryAPPSpringBoot.services.FilmService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Controller
@RequestMapping("/films")
public class FilmController {

    private final MemberRepository memberRepository;
    private final FilmService filmService;

    public FilmController(MemberRepository memberRepository, FilmService filmService) {
        this.memberRepository = memberRepository;
        this.filmService = filmService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("films", filmService.findAll());
        return "films/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model, @ModelAttribute("member") Member member) {
        model.addAttribute("film", filmService.findById(id));

        Optional<Member> filmOwner = filmService.getFilmOwner(id);

        if (filmOwner.isPresent())
            model.addAttribute("owner", filmOwner.get());
        else
            model.addAttribute("members", memberRepository.findAll());

        return "films/show";
    }

    @GetMapping("/new")
    public String create(@ModelAttribute("film") Film film) {
        return ("films/new");
    }

    @PostMapping("/new")
    public String save(@ModelAttribute("film") @Valid Film film, BindingResult bindingResult,
                       @RequestParam("image") MultipartFile file) {
        if (bindingResult.hasErrors())
            return ("films/new");

        filmService.save(film, file);
        return ("redirect:/films");
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("film", filmService.findById(id));
        return ("films/edit");
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable("id") int id, @ModelAttribute("film") @Valid Film film,
                         BindingResult bindingResult, @RequestParam("image") MultipartFile file) {
        if (bindingResult.hasErrors())
            return ("films/edit");

        filmService.update(id, film, file);
        return ("redirect:/films");
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        filmService.delete(id);
        return ("redirect:/films");
    }

    @PatchMapping("/{id}/release")
    public String release(@PathVariable("id") int id) {
        filmService.release(id);
        return "redirect:/films/" + id;
    }

    @PatchMapping("/{id}/assign")
    public String assign(@PathVariable("id") int id, @ModelAttribute("member") Member selectedMember) {
        filmService.assign(id, selectedMember);
        return "redirect:/films/" + id;
    }
}
