package com.csc340.jpacruddemo.news;

import com.csc340.jpacruddemo.research.Research;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller class.
 */
@Controller
@RequestMapping("/news")
public class NewsController {

    @Autowired
    NewsService newsService;

    /**
     * Get all news objects.
     * @param model Model
     * @return research
     */
    @GetMapping("/all")
    public String listNews(Model model) {
        model.addAttribute("newsList",
                newsService.getAllNews());
        return "news/list-news";
    }

    /**
     * Search for a research object using keyword.
     * @param model Model
     * @param keyword String
     * @return
     */
    @GetMapping("/news")
    public String getNews(Model model, @Param("keyword")String keyword){
        model.addAttribute("newsList",
                newsService.getAllNews(keyword));
        model.addAttribute("keyword",keyword);
        return "news/list-news";

    }

    /**
     * Search for news object using ID.
     * @param newsId Long
     * @param model Model
     * @return research
     */
    @GetMapping("/id={newsId}")
    public String getProduct(@PathVariable long newsId, Model model) {
        model.addAttribute("news",
                newsService.getNews(newsId));
        return "news/news-detail";
    }

    /**
     * Delete news using its id.
     * @param newsId Long
     * @param model Model
     * @return all
     */
    @GetMapping ("/delete/id={newsId}")
    public String deleteNews(@PathVariable long newsId, Model model){
        newsService.deleteNews(newsId);
        return "redirect:/news/all";
    }

    /**
     * Create a Research object.
     * @param news news
     * @param model model
     * @return all
     */
    @PostMapping("/create")
    public String createNews(News news, Model model) {
        String ticker = news.getTicker();
        if(newsService.isTickerExists(ticker)){
            model.addAttribute("error", "Ticker already exists!"); // Error message
            return "news/new-news";
        } else {
            newsService.saveNews(news);
            return "redirect:/news/all";
        }
    }

    /**
     * Update news object.
     * @param news news
     * @return all
     */
    @PostMapping ("/update")
    public String updateNews(News news){

        newsService.saveNews(news);
        return "redirect:/news/all";
    }

    /**
     * new news.
     * @param model model
     * @return new news form
     */
    @GetMapping("/new-news")
    public String newNewsForm(Model model){
        return "news/new-news";
    }

    /**
     * Update news by its id
     * @param newsId Long
     * @param model Model
     * @return News
     */
    @GetMapping("/update/id={newsId}")
    public String updateNewsForm(@PathVariable long newsId, Model model){
        model.addAttribute("news",
                newsService.getNews(newsId));
        return "news/update-news";
    }
}
