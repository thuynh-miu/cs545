package com.thuynh.lab4.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Logger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    LocalDate date;
    LocalTime time;
    String principle;
    String operation;

    public Logger(String p, String o) {
        this.date = LocalDate.now();
        this.time = LocalTime.now();
        this.principle = p;
        this.operation = o;
    }
}
