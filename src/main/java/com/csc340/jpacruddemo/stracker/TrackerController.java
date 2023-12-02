package com.csc340.jpacruddemo.stracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Salomon
 */
@Controller
@RequestMapping("/tracker")
public class TrackerController {
    //Create tracker service object to call its functions.
    @Autowired
    TrackerService trackerService;

    /**
     * Get all tracker objects.
     * @param model Model
     * @return tracker.
     */
    @GetMapping("/all")
    public String listTracker(Model model) {
        model.addAttribute("trackerList",
                trackerService.getAllTracker());
        return "tracker/list-tracker";
    }

    /**
     * Search for a tracker object using keyword.
     * @param model Model.
     * @param keyword String
     * @return tracker.
     */
    @GetMapping("/search")
    public String getTracker(Model model, @Param("keyword")String keyword){
        model.addAttribute("trackerList",
                trackerService.getAllTracker(keyword));
        model.addAttribute("keyword",keyword);
        return "tracker/list-tracker";

    }

    /**
     * Serach for tracker object using ID.
     * @param trackerId long.
     * @param model Model.
     * @return Tracker.
     */
    @GetMapping("/id={trackerId}")
    public String getProduct(@PathVariable long trackerId, Model model) {
        model.addAttribute("tracker",
                trackerService.getTracker(trackerId));
        return "tracker/tracker-detail";
    }

    /**
     * Delete Tracker using its id.
     * @param trackerId long
     * @param model Model
     * @return all.
     */
    @GetMapping ("/delete/id={trackerId}")
    public String deleteTracker(@PathVariable long trackerId, Model model){
        trackerService.deleteTracker(trackerId);
        return "redirect:/tracker/all";
    }

    /**
     * Create a Tracker object.
     * @param tracker Research.
     * @param model Model.
     * @return all.
     */
    @PostMapping("/create")
        public String createTracker(Tracker tracker, Model model) {
        String ticker = tracker.getTicker();
        if(trackerService.isTickerExists(ticker)){
            model.addAttribute("error", "Ticker already exists!"); // Error message
            return "tracker/new-tracker";
        } else {
            trackerService.saveTracker(tracker);
            return "redirect:/tracker/all";
        }
    }

    /**
     * Update tracker object.
     * @param tracker Tracker.
     * @return all.
     */
    @PostMapping ("/update")
    public String updateTracker(Tracker tracker){

        trackerService.saveTracker(tracker);
        return "redirect:/tracker/all";
    }

    /**
     * new tracker.
     * @param model model.
     * @return new tracker form.
     */
    @GetMapping("/new-tracker")
    public String newTrackerForm(Model model){
        return "tracker/new-tracker";
    }

    /**
     * Update research by its id.
     * @param trackerId long.
     * @param model Model.
     * @return Tracker.
     */
    @GetMapping("/update/id={trackerId}")
    public String updateTrackerForm(@PathVariable long trackerId, Model model){
        model.addAttribute("tracker",
                trackerService.getTracker(trackerId));
        return "tracker/update-tracker";
    }
}
