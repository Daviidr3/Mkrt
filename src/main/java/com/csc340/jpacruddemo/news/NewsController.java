package com.csc340.jpacruddemo.news;

import com.csc340.jpacruddemo.research.Research;
import com.csc340.jpacruddemo.research.ResearchService;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class NewsController {

    @Autowired
    NewsService newsService;
    @GetMapping("/all")
    public String listNews(Model model) {
        model.addAttribute("newsList",
                newsService.getAllNews());
        return "news/list-news";
    }

    @GetMapping("/news")
    public String getNews(Model model, @Param("keyword")String keyword){
        model.addAttribute("newsList",
                newsService.getAllNews(keyword));
        model.addAttribute("keyword",keyword);
        return "news/list-news";

    }
    @GetMapping("/id={newsId}")
    public String getProduct(@PathVariable long newsId, Model model) {
        model.addAttribute("news",
                newsService.getResearch(newsId));
        return "news/news-detail";
    }
    @GetMapping ("/delete/id={newsId}")
    public String deleteNews(@PathVariable long newsId, Model model){
        newsService.deleteNews(newsId);
        return "redirect:/news/all";
    }
    @PostMapping("/create")
    public String createNews(News news) {

        newsService.saveNews(news);
        return "redirect:/news/all";
    }
    @PostMapping ("/update")
    public String updateNews(News news){

        newsService.saveNews(news);
        return "redirect:/news/all";
    }

    @GetMapping("/new-news")
    public String newNewsForm(Model model){
        return "news/new-news";
    }

    @GetMapping("/update/id={newsId}")
    public String updateNewsForm(@PathVariable long newsId, Model model){
        model.addAttribute("news",
                newsService.getNews(newsId));
        return "news/update-news";
    }
}
