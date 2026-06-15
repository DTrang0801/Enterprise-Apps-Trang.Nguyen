package com.example.enterpriseapps.controller;

import com.example.enterpriseapps.dto.ContactMessage;
import jakarta.validation.Valid;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ContactController {

    private final JavaMailSender mailSender;

    public ContactController(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @GetMapping("/contact")
    public String contactForm(Model model) {
        model.addAttribute("contactMessage", new ContactMessage());
        return "contact";
    }

    @PostMapping("/contact")
    public String contactSubmit(@Valid @ModelAttribute("contactMessage") ContactMessage contactMessage,
                                 BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "contact";
        }

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo("admin@ngo-anderlecht.be");
            message.setFrom(contactMessage.getEmail());
            message.setSubject("Contactformulier: " + contactMessage.getNaam());
            message.setText("Van: " + contactMessage.getNaam() + " (" + contactMessage.getEmail() + ")\n\n"
                    + contactMessage.getBericht());
            mailSender.send(message);
            redirectAttributes.addFlashAttribute("success", "Uw bericht is succesvol verzonden!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Er is een fout opgetreden bij het verzenden.");
        }

        return "redirect:/contact";
    }
}
