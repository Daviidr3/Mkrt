package com.csc340.jpacruddemo.research;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Service class.
 */
@Service
public class ResearchService {

    @Autowired
    //create repo object.
    private ResearchRepository repo;
    //api link for stock information.
    private final String API_URL = "https://api.polygon.io/v2/aggs/ticker/";

    /**
     * Get all Research.
     * @return list of Research tickers from repo.
     */
    public List<Research> getAllResearch(){
        return repo.findAll();
    }

    /**
     * Get Research that matches keyword.
     * @param keyword the search term.
     * @return the list of research tickers.
     */
    public List<Research> getAllResearch(String keyword){
        if(keyword != null){
            return repo.search(keyword);

        }
        return repo.findAll();
    }

    /**
     * Searches research based on ID.
     * @param researchId long.
     * @return Research.
     */
    public Research getResearch(long researchId){
        return repo.getReferenceById(researchId);
    }

    /**
     * Deletes research object based on ID.
     * @param researchId long.
     */
    public void deleteResearch(long researchId){repo.deleteById(researchId);}

    /**
     * Saves Research object to database.
     * @param research Research
     */
    void saveResearch(Research research){repo.save(research);}

    /**
     * Retrieve stock information by making API calls to polygon.io
     * @param stockName String.
     * @param startDate String.
     * @param endDate String.
     * @return String Json information.
     */
    public String getResearchData(String stockName, String startDate, String endDate) {
        String apiKey = "Q1a614o4pUCpa3Xw6OuBtring stockName, String startDate, String endDate) {\n" +
"        String apiKey = \"QeAod65vsXbuA"; // Replace with your Polygon.io API key
        String apiUrl = API_URL + stockName + "/range/1/day/" + startDate + "/" + endDate +
                "?adjusted=true&sort=asc&limit=120&apiKey=" + apiKey;

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(apiUrl, String.class);
    }

    /**
     * Takes a String ticker and checks database if stock already exists.
     * @param ticker String
     * @return Boolean.
     */
    public boolean isTickerExists(String ticker) {
        List<Research> researchList = repo.findByTicker(ticker);
        return !researchList.isEmpty(); // If list is not empty, ticker exists
    }
}



