package com.mycompany.app.newgoogldocs.Controllers;

import com.mycompany.app.newgoogldocs.Data.Services.DocumentService;
import com.mycompany.app.newgoogldocs.Models.Document;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Controller
@AllArgsConstructor
public class PageController {

    DocumentService documentService;

    @GetMapping("/")
    public String index() {
        return "index";
    }
    
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/dashboard")
    public String dashboard(Authentication authentication, Model model) {
        model.addAttribute("email", authentication.getName());
        return "dashboard";
    }

    @GetMapping("/documents")
    @PreAuthorize("hasRole('USER')")
    public String documents(Authentication authentication, Model model) {
        Set<String> ownerIds = new HashSet<String>();
        ownerIds.add(authentication.getPrincipal().toString());
        model.addAttribute("documents", documentService.getByOwnerIds(ownerIds));

        return "documents";
    }

    @GetMapping("/document_editor/{docId}")
    @PreAuthorize("hasRole('USER')")
    public String documentEditor(@PathVariable String docId,  Authentication authentication, Model model) {

        Document doc = documentService.getDocument(docId);
        if(!doc.getAuthorIds().contains(authentication.getPrincipal().toString())) {
            return "redirect:/";
        }

        model.addAttribute("doc", doc);
        return "documentEditor";

    }


}
