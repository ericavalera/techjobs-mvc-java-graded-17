package org.launchcode.techjobsmvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;

/**
 * Created by LaunchCode
 */
@Controller
public class HomeController {
// renders the index page & the corresponding template is templates/index.html
    @GetMapping(value = "/")
    public String index(Model model) {

        HashMap<String, String> actionChoices = new HashMap<>();
        actionChoices.put("search", "Search");
        actionChoices.put("list", "List");
        // model.addAttribute updates index page with "Search, List" links
        // that are shown on actionChoices HashMap
        model.addAttribute("actions", actionChoices);

        return "index";
    }

}

