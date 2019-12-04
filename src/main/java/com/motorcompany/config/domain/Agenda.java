package com.motorcompany.config.domain;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.RowId;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@SequenceGenerator(name="seq", initialValue=1, allocationSize=999999999)
@JsonDeserialize
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonAutoDetect
@Entity
//@IdClass(value = Agenda.class)
public class Agenda extends AbstractEntity {



    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int agendaId ;

    @Column()
    private String subject;

    @JsonIgnoreProperties(value = {"parentActivity"})
    @OneToMany( fetch = FetchType.LAZY,targetEntity = Vote.class, cascade = CascadeType.ALL)
    private Set<Vote> vote= new HashSet<Vote>();

    @JsonIgnoreProperties(value = {"parentActivity"})
    @OneToOne( fetch = FetchType.LAZY,targetEntity = Result.class, cascade = CascadeType.ALL)
    private Result result;

    @JsonIgnoreProperties(value = {"parentActivity"})
    @OneToOne( fetch = FetchType.LAZY,targetEntity = Session.class, cascade = CascadeType.ALL)
    private Session session;

    public void setAgendaId(int agendaId) {
        this.agendaId = agendaId;
    }

    public long getAgendaId() {
        return agendaId;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Session getSession() {        return session;    }

    public void setResult(Result result) {
        this.result = result;
    }

    public Result getResult() {
        return result;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Set<Vote> getVote() {  return vote;    }

    public void setVote(Vote vote) {
        this.vote.add(vote);
    }

    public Agenda(String subject) {



        this.subject = subject;
        this.vote = vote;
    }

    public Agenda() {
        this.vote = vote;
        this.result = new Result();
        this.session = new Session();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Agenda agenda = (Agenda) o;
        return agendaId == agenda.agendaId &&
                Objects.equals(subject, agenda.subject) &&
                Objects.equals(vote, agenda.vote) &&
                Objects.equals(result, agenda.result) &&
                Objects.equals(session, agenda.session);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), agendaId, subject, vote, result, session);
    }

    @Override
    public String toString() {
        return "Agenda{" +
                "subject='" + subject + '\'' +
                ", vote=" + vote +
                ", result=" + result +
                ", session=" + session +
                '}';
    }
}