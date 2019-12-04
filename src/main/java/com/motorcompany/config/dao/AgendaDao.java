package com.motorcompany.config.dao;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.motorcompany.config.domain.Agenda;

import java.util.List;

@Repository
public interface AgendaDao extends CrudRepository<Agenda, Long> {
    List<Agenda> findAll();
    Agenda findByAgendaId(long agendaId);

}