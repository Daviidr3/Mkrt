package com.csc340.jpacruddemo.research;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


/**
 * Manages database Query.
 * @author David Vasquez
 */
public interface ResearchRepository extends JpaRepository<Research, Long> {

    public List<Research> findByTicker(String ticker);

    @Query("SELECT r FROM Research r WHERE r.ticker LIKE %?1%")
    public List<Research> search(String keyword);

}