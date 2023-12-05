package com.csc340.jpacruddemo.research;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Api controller class, handles api requests for website.
 */
@Controller
public class ResearchAPIController {
    @Autowired
    private ResearchService researchService;

    /**
     *
     * @return research testing.
     */
    @GetMapping("/search-research")
    public String searchResearch() {
        return "research/testing";
    }

    /**
     * Retrieves stock data service class from polygon.io.
     * @param ticker String
     * @param startDate String
     * @param endDate String
     * @param model String
     * @return String json of stock information from polygon.
     */
    @GetMapping("/getResearchData")
    public String getApiData(   @RequestParam("ticker") String ticker,
                                 @RequestParam("startDate") String startDate,
                                 @RequestParam("endDate") String endDate,
                             Model model) {
        String data = researchService.getResearchData(ticker,startDate,endDate);

        model.addAttribute("researchTicker", ticker);
        model.addAttribute("data", data);
        return "research/testing";
    }

    /**
     * Get Top 20 gainers from polygon.io
     * @param model Model.
     * @return String json of stock information from polygon.
     */
    @GetMapping("/getGainers")
    public String getGainers (Model model){
        String data = researchService.getGainers();
        model.addAttribute("data", data);
        return "research/list-research";
    }
}