package com.csc340.jpacruddemo.news;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class NewsAPIController {
    @Autowired
    private NewsService newsService;

    @GetMapping("/getNewsData")
    public String getApiData(@RequestParam("ticker") String ticker, Model model){
        String data = newsService.getNewsData(ticker);

        model.addAttribute("newsTicker", ticker);
        model.addAttribute("data", data);
        return "news/testing";
    }

}
