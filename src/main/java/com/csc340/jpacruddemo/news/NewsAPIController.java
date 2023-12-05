package com.csc340.jpacruddemo.news;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Api controller class, handles api requests for website.
 */
@Controller
public class NewsAPIController {
    @Autowired
    private NewsService newsService;

    /**
     *
     * @return request testing
     */
    @GetMapping("/search-news")
    public String searchNews() {

        return "news/testing";
    }

    /**
     * Makes an API request for general news.
     * @param model Model
     * @return news.
     */
    @GetMapping("/getNewsData")
    public String getApiData(Model model) {
        String data = newsService.getNewsData();
        model.addAttribute("data", data);
        return "news/testing";
    }

    /**
     * Makes an API request for specific ticker.
     * @param ticker String.
     * @param model Model.
     * @return
     */
    @GetMapping("/getNewsDataTicker")
    public String getApiData(String ticker , Model model) {
        String data = newsService.getNewsData(ticker);
        model.addAttribute("ticker",ticker);
        model.addAttribute("data", data);
        return "news/list-news";
    }
}
