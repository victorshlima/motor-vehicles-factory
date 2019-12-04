package com.motorcompany.config.dao;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.motorcompany.config.domain.Vote;

import java.util.List;

@Repository
public interface VoteDao extends CrudRepository<Vote, Long> {
    List<Vote> findAll();
    Vote save(Vote vote);
    long countAllByAgendaIdAndVoteOptionEquals(long AgendaId, String voteOption);



}

