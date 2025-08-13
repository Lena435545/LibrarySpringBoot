package com.library.LibraryAPPSpringBoot.controllers;

import com.library.LibraryAPPSpringBoot.models.Account;
import com.library.LibraryAPPSpringBoot.services.RegistrationService;
import com.library.LibraryAPPSpringBoot.utils.AccountValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final AccountValidator accountValidator;
    private final RegistrationService registrationService;

    @Autowired
    public AuthController(AccountValidator accountValidator, RegistrationService registrationService) {
        this.accountValidator = accountValidator;
        this.registrationService = registrationService;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }

    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("account") Account account) {
        return "auth/registration";
    }

    @PostMapping("/registration")
    public String performRegistration(@ModelAttribute("account") @Valid Account account,
                                      BindingResult bindingResult) {
        accountValidator.validate(account, bindingResult);

        if (bindingResult.hasErrors())
            return "/auth/registration";

        registrationService.register(account);

        return "redirect:/auth/login";
    }

    @GetMapping("/access_denied")
    public String denyAccess(){
        return "auth/access_denied";
    }

}
