package com.csc340.jpacruddemo.stracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
/**
 *
 * @author Salomon
 */
    @Service
public class TrackerService {

    @Autowired
    //create repo object.
    private TrackerRepository repo;
    //api link for stock information.
    private final String API_URL = "https://api.polygon.io/v2/aggs/ticker/";

    /**
     * Get all Trackers.
     * @return list of Tracker tickers from repo.
     */
    public List<Tracker> getAllTracker(){
        return repo.findAll();
    }

    /**
     * Get Trackers that matches keyword.
     * @param keyword the search term.
     * @return the list of tracker tickers.
     */
    public List<Tracker> getAllTracker(String keyword){
        if(keyword != null){
            return repo.search(keyword);

        }
        return repo.findAll();
    }

    /**
     * Searches tracker based on ID.
     * @param trackerId long.
     * @return Tracker.
     */
    public Tracker getTracker(long trackerId){
        return repo.getReferenceById(trackerId);
    }

    /**
     * Deletes research object based on ID.
     * @param trackerId long.
     */
    public void deleteTracker(long trackerId){repo.deleteById(trackerId);}

    /**
     * Saves Research object to database.
     * @param tracker Research
     */
    void saveTracker(Tracker tracker){repo.save(tracker);}

     /**
     * Takes a String ticker and checks database if stock already exists.
     * @param ticker String
     * @return Boolean.
     */
    public boolean isTickerExists(String ticker) {
        List<Tracker> trackerList = repo.findByTicker(ticker);
        return !trackerList.isEmpty(); // If list is not empty, ticker exists
    }
}
