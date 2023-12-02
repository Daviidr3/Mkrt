package com.csc340.jpacruddemo.research;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author David Vasquez
 */
@Entity
@Table(name = "research")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
/**
 * Research object to hold a string of user's stock ticker and user id.
 */
public class Research {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String ticker;

    public Research(String ticker){
        this.ticker = ticker;
    }
}
