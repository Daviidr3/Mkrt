package com.csc340.jpacruddemo.research;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller class.
 */
@Controller
@RequestMapping("/research")
public class ResearchController {
    //Create research service object to call its functions.
    @Autowired
    ResearchService researchService;

    /**
     * Get all research objects.
     * @param model Model
     * @return research.
     */
    @GetMapping("/all")
    public String listResearch(Model model) {
        model.addAttribute("researchList",
                researchService.getAllResearch());
        return "research/list-research";
    }

    /**
     * Search for a research object using keyword.
     * @param model Model.
     * @param keyword String
     * @return research.
     */
    @GetMapping("/search")
    public String getResearch(Model model, @Param("keyword")String keyword){
        model.addAttribute("researchList",
                researchService.getAllResearch(keyword));
        model.addAttribute("keyword",keyword);
        return "research/list-research";

    }

    /**
     * Search for research object using ID.
     * @param researchId long.
     * @param model Model.
     * @return Research.
     */
    @GetMapping("/id={researchId}")
    public String getProduct(@PathVariable long researchId, Model model) {
        model.addAttribute("research",
                researchService.getResearch(researchId));
        return "research/research-detail";
    }

    /**
     * Delete Research using its id.
     * @param researchId long
     * @param model Model
     * @return all.
     */
    @GetMapping ("/delete/id={researchId}")
    public String deleteResearch(@PathVariable long researchId, Model model){
        researchService.deleteResearch(researchId);
        return "redirect:/research/all";
    }

    /**
     * Create a Research object.
     * @param research Research.
     * @param model Model.
     * @return all.
     */
    @PostMapping("/create")
        public String createResearch(Research research, Model model) {
        String ticker = research.getTicker();
        if(researchService.isTickerExists(ticker)){
            model.addAttribute("error", "Ticker already exists!"); // Error message
            return "research/new-research";
        } else {
            researchService.saveResearch(research);
            return "redirect:/research/all";
        }
    }

    /**
     * Update research object.
     * @param research Research.
     * @return all.
     */
    @PostMapping ("/update")
    public String updateResearch(Research research){

        researchService.saveResearch(research);
        return "redirect:/research/all";
    }

    /**
     * new research.
     * @param model model.
     * @return new research form.
     */
    @GetMapping("/new-research")
    public String newResearchForm(Model model){
        return "research/new-research";
    }

    /**
     * Update research by its id.
     * @param researchId long.
     * @param model Model.
     * @return Research.
     */
    @GetMapping("/update/id={researchId}")
    public String updateResearchForm(@PathVariable long researchId, Model model){
        model.addAttribute("research",
                researchService.getResearch(researchId));
        return "research/update-research";
    }



}


