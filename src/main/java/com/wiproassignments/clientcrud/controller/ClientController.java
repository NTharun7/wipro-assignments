package com.wiproassignments.clientcrud.controller;

import com.wiproassignments.clientcrud.model.Client;
import com.wiproassignments.clientcrud.repository.ClientRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientRepository clientRepo;

    // Show all clients
    @GetMapping
    public String viewHomePage(Model model) {
        model.addAttribute("clients", clientRepo.findAll());
        return "index";
    }

    // Show form to add new client
    @GetMapping("/new")
    public String showAddForm(Client client) {
        return "add-client";
    }

    // Handle new client submission
    @PostMapping("/add")
    public String addClient(@Valid Client client, BindingResult result) {
        if (result.hasErrors()) {
            return "add-client";
        }
        clientRepo.save(client);
        return "redirect:/clients";
    }

    // Show form to update client
    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Client client = clientRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid client ID: " + id));
        model.addAttribute("client", client);
        return "edit-client";
    }

    // Handle client update submission
    @PostMapping("/update/{id}")
    public String updateClient(@PathVariable("id") long id, @Valid Client client, BindingResult result) {
        if (result.hasErrors()) {
            client.setId(id);
            return "edit-client";
        }
        clientRepo.save(client);
        return "redirect:/clients";
    }

    // Handle delete client
    @GetMapping("/delete/{id}")
    public String deleteClient(@PathVariable("id") long id) {
        Client client = clientRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid client ID: " + id));
        clientRepo.delete(client);
        return "redirect:/clients";
    }
}
