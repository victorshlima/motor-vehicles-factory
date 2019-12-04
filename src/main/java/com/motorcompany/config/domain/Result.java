package com.motorcompany.config.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.persistence.Column;
import javax.persistence.Entity;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonAutoDetect
@Entity
public class Result extends AbstractEntity {


    @Column(nullable = true)
    private long votesTotalYes;

    @Column(nullable = true)
    private long votesTotalNo;

    @Column(nullable = true)
    private long votesTotal;

    @Column(nullable = true)
    private String WinnerChoice;

    public void setWinnerChoice(String winnerChoice) {
        WinnerChoice = winnerChoice;
    }

    public long getVotesTotalYes() {
        return votesTotalYes;
    }

    public void setVotesTotalYes(long votesTotalYes) {
        this.votesTotalYes = votesTotalYes;
    }

    public long getVotesTotalNo() {
        return votesTotalNo;
    }

    public void setVotesTotalNo(long votesTotalNo) {
        this.votesTotalNo = votesTotalNo;
    }

    public long getVotesTotal() {
        return votesTotal;
    }

    public void setVotesTotal(int votesTotal) {
        this.votesTotal = votesTotal;
    }

    @Override
    public String toString() {
        return "Result{" +
                "votesTotalYes=" + votesTotalYes +
                ", votesTotalNo=" + votesTotalNo +
                ", votesTotal=" + votesTotal +
                ", WinnerChoice='" + WinnerChoice + '\'' +
                '}';
    }
}