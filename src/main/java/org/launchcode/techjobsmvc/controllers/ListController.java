package org.launchcode.techjobsmvc.controllers;

import org.launchcode.techjobsmvc.models.Job;
import org.launchcode.techjobsmvc.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by LaunchCode
 */

//marks this class as a Controller
@Controller
//maps to list to methods in this controller
@RequestMapping(value = "list")
public class ListController {
    // hashmap adds the columnChoices to the list page (when loaded are the titles)
    static HashMap<String, String> columnChoices = new HashMap<>();
    static HashMap<String, Object> tableChoices = new HashMap<>();

    public ListController () {
        // adding to the HashMap columnChoices
        columnChoices.put("all", "All");
        columnChoices.put("employer", "Employer");
        columnChoices.put("location", "Location");
        columnChoices.put("positionType", "Position Type");
        columnChoices.put("coreCompetency", "Skill");

//        TASK 2: modified for list.html to include an "View All" link
        //adding to HashMap tableChoices
        tableChoices.put("all", "View All") ;
        tableChoices.put("employer", JobData.getAllEmployers());
        tableChoices.put("location", JobData.getAllLocations());
        tableChoices.put("positionType", JobData.getAllPositionTypes());
        tableChoices.put("coreCompetency", JobData.getAllCoreCompetency());
    }
    // GetMapping  HTTP request method "GET" used for retrieving data
    @GetMapping(value = "")
    // Model model, interface that defines a holder for model attributes
    public String list(Model model) {
        // model.addAttribute adds data to be available to the view/accessed
        //by ThymeLeaf via key/value pair attribute name being the key.
        model.addAttribute("columns", columnChoices);
        model.addAttribute("tableChoices", tableChoices);
        //uses methods in JobData to load information
        model.addAttribute("employers", JobData.getAllEmployers());
        model.addAttribute("locations", JobData.getAllLocations());
        model.addAttribute("positions", JobData.getAllPositionTypes());
        model.addAttribute("skills", JobData.getAllCoreCompetency());

        return "list";
    }
    //maps to list-jobs
    @GetMapping(value = "jobs")
    //@RequestParam is used to extract individual parameters from the submitted/request url
    // required = false, a boolean to set whether the parameter is required
    public String listJobsByColumnAndValue(Model model, @RequestParam String column, @RequestParam(required = false) String value) {
        ArrayList<Job> jobs;
        //if equals all, fetches all job information
        if (column.equals("all")){
            jobs = JobData.findAll();
            model.addAttribute("title", "All Jobs");
        } else {
            //else fetches job information by column/ value using the method in JobData
            jobs = JobData.findByColumnAndValue(column, value);
            model.addAttribute("title", "Jobs with " + columnChoices.get(column) + ": " + value);
        }
        model.addAttribute("jobs", jobs);

        return "list-jobs";
    }
}

