package com.csc340.jpacruddemo.stracker;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 *
 * @author Salomon
 */
public interface TrackerRepository extends JpaRepository<Tracker, Long> {

    public List<Tracker> findByTicker(String ticker);

    @Query("SELECT t FROM Tracker t WHERE t.ticker LIKE %?1%")
    public List<Tracker> search(String keyword);
}
