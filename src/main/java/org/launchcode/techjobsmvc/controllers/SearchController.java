package org.launchcode.techjobsmvc.controllers;

import org.launchcode.techjobsmvc.models.Job;
import org.launchcode.techjobsmvc.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;

import static org.launchcode.techjobsmvc.controllers.ListController.columnChoices;


/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @GetMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        return "search";
    }

    // TODO #3 - Create a handler to process a search request and render the updated search view.

//    this method will process the form and display results
         @PostMapping("results")
         public String displaySearchResults(Model model, @RequestParam String searchType,
                                       @RequestParam String searchTerm){

        // this is used to store search results
        ArrayList<Job> jobs;
        //if there is no search term or search type radio button "all" is selected
        if(searchTerm.isEmpty() && searchType.equals("all")){
            //jobs will use findall() method to retrieve all the job data
            jobs = JobData.findAll();
        // else if radio button all is selected plus "search term" user entry
        } else if(searchType.equals("all")) {
            jobs = JobData.findByValue(searchTerm);
            // else selected radio button search type/ and user entry/search term
        } else {
            jobs = JobData.findByColumnAndValue(searchType, searchTerm);
        }

        // adds search results to the search.html/fragments
        model.addAttribute("jobs", jobs);

        // added for the fragments to work on search.html
        //adds search options
        model.addAttribute("columns", ListController.columnChoices);
//        model.addAttribute("employers", JobData.getAllEmployers());
//        model.addAttribute("locations", JobData.getAllLocations());
//        model.addAttribute("positions", JobData.getAllPositionTypes());
//        model.addAttribute("skills", JobData.getAllCoreCompetency());

        // displays title Jobs with "___" : _____
        model.addAttribute("title", "Jobs with " + searchType + " : " + searchTerm);
        //returns the search results
        return "search";
    }
}

