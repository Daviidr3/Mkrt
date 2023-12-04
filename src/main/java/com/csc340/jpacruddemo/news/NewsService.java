package com.csc340.jpacruddemo.news;

import com.csc340.jpacruddemo.research.Research;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Service class
 */
@Service
public class NewsService {
    private final String API_URL_GEN = "https://api.polygon.io/v2/reference/news?apiKey=0fSfNph40VfB_3sbPi3f8Oc1Jj0vKqMF";
    private final String API_URL="https://api.polygon.io/v2/reference/news?ticker=";
    @Autowired
    //Create repo object
    private NewsRepository repo;

    /**
     * Get all News.
     * @return list of news tickers from repo.
     */
    public List<News> getAllNews(){
        return repo.findAll();
    }

    /**
     * Get News that matches keyword.
     * @param keyword the search term.
     * @return the list of news tickers.
     */
    public List<News> getAllNews(String keyword){
        if(keyword != null){
            return repo.search(keyword);

        }
        return repo.findAll();
    }

    /**
     * Searches news based on ID.
     * @param newsId Long
     * @return news
     */
    public Object getNews(long newsId){

        return repo.getReferenceById(newsId);
    }

    /**
     * Deletes news object based on ID.
     * @param newsId long
     */
    public void deleteNews(long newsId){
        repo.deleteById(newsId);
    }

    /**
     * Saves news object to database.
     * @param news news
     */
    void saveNews(News news){

        repo.save(news);
    }

    /**
     * Takes a String ticker and checks database if stock already exists.
     * @param ticker String
     * @return boolean
     */
    public boolean isTickerExists(String ticker) {
        List<News> newsList = repo.findByTicker(ticker);
        return !newsList.isEmpty(); // If list is not empty, ticker exists
    }

    /**
     * General news feed.
     * @return news json.
     */
   public String getNewsData() {
       String apiKey = "Q1a614o4pUCpa3Xw6OuBeAod65vsXbuA";
       String apiUrl = API_URL_GEN + apiKey;

       RestTemplate restTemplate = new RestTemplate();
       return restTemplate.getForObject(apiUrl, String.class);
   }

    /**
     * news on specific stock ticker.
     * @param ticker String
     * @return news json.
     */
    public String getNewsData(String ticker) {
        String apiKey = "&apiKey=Q1a614o4pUCpa3Xw6OuBeAod65vsXbuAQ1a614o4pUCpa3Xw6OuBeAod65vsXbuA";
        String apiUrl = API_URL+ticker+apiKey;

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(apiUrl, String.class);
    }
}
