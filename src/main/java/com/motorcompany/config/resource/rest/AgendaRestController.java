//package com.motorcompany.config.resource.rest;
//
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
//import com.motorcompany.config.dao.AgendaDao;
//import com.motorcompany.config.dao.SessionDao;
//import com.motorcompany.config.dao.VoteDao;
//import com.motorcompany.config.domain.*;
//import restvote.service.VotationService;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
//import java.net.URI;
//import java.util.List;
//import java.util.Optional;
//
//@EnableSwagger2
//@RestController
//@RequestMapping( "v1")
//public class AgendaRestController {
//
//    static final Logger logger = LogManager.getLogger(AgendaRestController.class.getName());
//    @Autowired
//    private VotationService votationService;
//    private final AgendaDao agendaDao;
//    private final SessionDao sessionDao;
//    private final VoteDao voteDao;
//
//    @Autowired
//    public AgendaRestController(AgendaDao agendaDao, VoteDao voteDao, SessionDao sessionDao)
//    {
//        this.agendaDao = agendaDao;
//        this.sessionDao =  sessionDao;
//        this.voteDao =  voteDao;
//    }
//
//
//   @PostMapping("/agendas")
//    public ResponseEntity<Void> AddAgenda(@RequestBody Agenda agenda) {
//        votationService.CreateAgenda(agenda);
//    return ResponseEntity.created(genericURIPostPutLocation(agenda)).build();
//    }
//
//    @GetMapping("/agendas/{agendaId}")
//    @ResponseStatus(HttpStatus.OK)
//    public Agenda AgendaFindAllById(@PathVariable("agendaId") long agendaId)
//    {
//        return agendaDao.findByAgendaId(agendaId);
//    }
//
//
//    @PostMapping("/sessions")
//    public ResponseEntity<Void> CreateSession( @RequestBody Session session) {
//        votationService.CreateSession(session);
//        return ResponseEntity.created(genericURIPostPutLocation(session)).build();
//    }
//
//
//    @PostMapping("/sessions/{agendaId}")
//    public ResponseEntity<Void> openSession(@PathVariable("agendaId") long agendaId, @RequestBody Session session ) {
//        votationService.OpenSession(agendaId, session);
//        return ResponseEntity.created(genericURIPostPutLocation(session)).build();
//    }
//
//
//
//    @PostMapping("/votations/{agendaId}")
//    public ResponseEntity<Void> AddVote(@PathVariable("agendaId") long agendaId,
//                                        @RequestBody Vote vote ) {
//        votationService.AddVote(agendaId, vote);
//        return ResponseEntity.created(genericURIPostPutLocation(vote)).build();
//    }
//
//    @GetMapping("/agendas")
//    @ResponseStatus(HttpStatus.OK)
//    public List<Agenda> AgendaFindAll() {
//        return agendaDao.findAll();
//    }
//
//    @GetMapping("/votations")
//    @ResponseStatus(HttpStatus.OK)
//    public List<Vote> VotesFindAll() {
//        return voteDao.findAll();
//    }
//
//    @GetMapping("/sessions")
//    @ResponseStatus(HttpStatus.OK)
//    public List<Session> SessionsFindAll() {
//        return sessionDao.findAll();
//    }
//
//    @GetMapping("/session")
//    @ResponseStatus(HttpStatus.OK)
//    public Optional<Session> GetSessionById(@PathVariable("id") long id){
//        return  sessionDao.findById(id);
//    }
//
//    @GetMapping("/results")
//    @ResponseStatus(HttpStatus.OK)
//    public Result GetResultAndCloseSession(@PathVariable("id") long id){
//        return  votationService.endSession(id);
//    }
//
//    private URI genericURIPostPutLocation ( AbstractEntity entity){
//        logger.trace(entity.getId());
//        URI Location  = ServletUriComponentsBuilder
//                .fromCurrentRequest()
//                .path("/{id}")
//                .buildAndExpand(entity.getId())
//                .toUri();
//        return Location;
//    }
//
//}
