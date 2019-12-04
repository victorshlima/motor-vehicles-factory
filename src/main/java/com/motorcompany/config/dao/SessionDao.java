package com.motorcompany.config.dao;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.motorcompany.config.domain.Session;

import java.util.List;

@Repository
public interface SessionDao extends CrudRepository<Session, Long> {
    List<Session> findAll();
    Session save(Session session);
    Session findAllByAgendaId(long id);
}