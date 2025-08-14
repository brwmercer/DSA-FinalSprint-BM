package org.example.bst.controller;

import org.example.bst.model.TreeEntry;
import org.example.bst.service.BSTService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BSTController {

    private final BSTService service;

    public BSTController(BSTService service) {
        this.service = service;
    }

    @GetMapping("/enter-numbers")
    public String enterNumbersPage() {
        return "enter-numbers";
    }

    @PostMapping("/process-numbers")
    public String processNumbers(@RequestParam String numbers, Model model) {
        try {
            TreeEntry entry = service.processNumbers(numbers, false);
            model.addAttribute("entry", entry);
            return "result";
        } catch (Exception e) {
            model.addAttribute("error", "Invalid input: " + e.getMessage());
            return "enter-numbers";
        }
    }

    @GetMapping("/previous-trees")
    public String showPrevious(Model model) {
        List<TreeEntry> entries = service.getAllEntries();
        model.addAttribute("entries", entries);
        return "previous-trees";
    }
}
