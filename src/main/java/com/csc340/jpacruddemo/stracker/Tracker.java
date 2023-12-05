package com.csc340.jpacruddemo.stracker;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Salomon
 */
@Entity
@Table(name = "tracker")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
/**
 * Create the object (ticker) to hold the appropriate API information.
 */
public class Tracker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;
    private String ticker;

    public Tracker(String ticker) {
        this.ticker = ticker;
    }
}
