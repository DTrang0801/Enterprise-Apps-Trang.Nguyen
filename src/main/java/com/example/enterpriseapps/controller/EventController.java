package com.example.enterpriseapps.controller;

import com.example.enterpriseapps.model.Event;
import com.example.enterpriseapps.model.Location;
import com.example.enterpriseapps.repository.EventRepository;
import com.example.enterpriseapps.repository.LocationRepository;
import jakarta.validation.Valid;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.beans.PropertyEditorSupport;
import java.util.List;

@Controller
public class EventController {

    private final EventRepository eventRepository;
    private final LocationRepository locationRepository;

    public EventController(EventRepository eventRepository, LocationRepository locationRepository) {
        this.eventRepository = eventRepository;
        this.locationRepository = locationRepository;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Location.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                if (text == null || text.isEmpty()) {
                    setValue(null);
                } else {
                    setValue(locationRepository.findById(Long.parseLong(text)).orElse(null));
                }
            }
        });
    }

    @GetMapping("/")
    public String index(Model model) {
        List<Event> events = eventRepository.findAllByOrderByTijdstipDesc(PageRequest.of(0, 10));
        model.addAttribute("events", events);
        return "index";
    }

    @GetMapping("/new")
    public String newEventForm(Model model) {
        model.addAttribute("event", new Event());
        model.addAttribute("locations", locationRepository.findAll());
        return "new-event";
    }

    @PostMapping("/new")
    public String newEventSubmit(@Valid @ModelAttribute("event") Event event,
                                  BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("locations", locationRepository.findAll());
            return "new-event";
        }
        eventRepository.save(event);
        return "redirect:/";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Evenement niet gevonden"));
        model.addAttribute("event", event);
        return "details";
    }
}
