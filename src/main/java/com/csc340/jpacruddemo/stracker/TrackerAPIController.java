package com.csc340.jpacruddemo.stracker;

import com.csc340.jpacruddemo.research.ResearchService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Salomon
 */
@Controller
public class TrackerAPIController {
    private TrackerService trackerService;

    /**
     *
     * @return research testing.
     */
    @GetMapping("/search-tracker")
    public String searchTracker() {
        return "research/testing";
    }

    /**
     * Retrieves stock data service class from polygon.io.
     * @param ticker String
     * @param model String
     * @return String json of stock information from polygon.
     */
    @GetMapping("/getTrackerData")
    public String getApiData(   @RequestParam("ticker") String ticker,
                                Model model) {
        String data = trackerService.getTrackerData(ticker);

        model.addAttribute("researchTicker", ticker);
        model.addAttribute("data", data);
        return "tracker/home-page";
    }

}