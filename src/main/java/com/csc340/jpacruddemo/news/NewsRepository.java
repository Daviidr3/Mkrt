package com.csc340.jpacruddemo.news;

import com.csc340.jpacruddemo.research.Research;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {

    public List<News> findByTicker(String ticker);

    @Query("SELECT n FROM News n WHERE n.ticker LIKE %?1%")
    public List<News> search(String keyword);
}
