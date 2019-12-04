package com.motorcompany.config.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.persistence.Column;
import javax.persistence.Entity;


@JsonDeserialize
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonAutoDetect
@Entity
public class Session extends AbstractEntity {

    @Column(nullable = true)
    private long agendaId;

    @Column(nullable = true)
    private String sessionStatus;

    @Column()
    private long startVotation;

    @Column()
    private long endVotation;

    @Column()
    private Integer durationMinutes;

    public String getSessionStatus() {
        return sessionStatus;
    }

    public void setSessionStatus(String sessionStatus) {
        this.sessionStatus = sessionStatus;
    }

    public long getAgendaId() { return agendaId;    }

    public void setAgendaId(long agendaId) { this.agendaId = agendaId;    }

    public long getStartVotation() { return startVotation;    }

    public void setStartVotation(long startVotation) { this.startVotation = startVotation;    }

    public long getEndVotation() {  return endVotation;    }

    public void setEndVotation(long endVotation) {  this.endVotation = endVotation;    }

    public Integer getDurationMinutes() {     return durationMinutes;
    }

    public void setDurationMinutes(Integer durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    @Override
    public String toString() {
        return "Session{" +
                "agendaId=" + agendaId +
                ", sessionStatus='" + sessionStatus + '\'' +
                ", startVotation=" + startVotation +
                ", endVotation=" + endVotation +
                ", durationMinutes=" + durationMinutes +
                '}';
    }
}