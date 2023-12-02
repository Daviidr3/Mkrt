package com.csc340.jpacruddemo.stracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
/**
 *
 * @author Salomon
 */
public class TrackerService {
    @Service
public class ResearchService {

    @Autowired
    //create repo object.
    private TrackerRepository repo;
    //api link for stock information.
    private final String API_URL = "https://api.polygon.io/v2/aggs/ticker/";

    /**
     * Get all Research.
     * @return list of Research tickers from repo.
     */
    public List<Tracker> getAllResearch(){
        return repo.findAll();
    }

    /**
     * Get Research that matches keyword.
     * @param keyword the search term.
     * @return the list of research tickers.
     */
    public List<Tracker> getAllResearch(String keyword){
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
    public Tracker getTracker(long researchId){
        return repo.getTrackerById(researchId);
    }

    /**
     * Deletes research object based on ID.
     * @param researchId long.
     */
    public void deleteTracker(long researchId){repo.deleteById(trackerId);}

    /**
     * Saves Research object to database.
     * @param research Research
     */
    void saveTracker(Tracker tracker){repo.save(tracker);}

}
