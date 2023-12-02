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


}
