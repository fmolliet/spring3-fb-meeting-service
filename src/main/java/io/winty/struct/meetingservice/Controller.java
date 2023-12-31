package io.winty.struct.meetingservice;

import java.util.logging.Level;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.winty.struct.meetingservice.dto.CreateMeetingDTO;
import io.winty.struct.meetingservice.dto.MeetingDTO;
import io.winty.struct.meetingservice.dto.UpdateMeetingDTO;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.extern.java.Log;

@Log
@RestController
@RequestMapping("/meeting")
public class Controller {

    @Autowired
    private Repository repository;
    
    @GetMapping
    public Page<MeetingDTO> list(@PageableDefault(size = 1000) Pageable page){
        log.log(Level.INFO, "LIST: {0}", page);
        return repository.findAllByActiveTrue(page).map(MeetingDTO::new);
    }
    
    @GetMapping ("/{id}") 
    public MeetingDTO get(@PathVariable("id") String snowflake) {
        log.log(Level.INFO, "GET: {0}", snowflake);
        try {
            return new MeetingDTO(repository.findBySnowflake(snowflake));
        } catch ( Exception ex ){
            log.log(Level.WARNING, "Erro ao buscar por snowflake, não localizado.");
            return null;
        }
    }
    
    @GetMapping ("state/{state}") 
    public Page<MeetingDTO> getByState(@PageableDefault(size = 1000) Pageable page, @PathVariable("state") String state) {
        log.log(Level.INFO, "GET STATE: {0}", state);
        try {
            return repository.findAllByActiveTrueAndState(page, state).map(MeetingDTO::new);
        } catch ( Exception ex ){
            log.log(Level.WARNING, "Erro ao buscar por estado, não localizado.");
            return null;
        }
    }
    
    @PostMapping
    @Transactional  
    public void create(@RequestBody @Valid CreateMeetingDTO meeting){
        log.log(Level.INFO, "CREATE IN: {0}", meeting.toString());
        repository.save(new Meeting(meeting));
    }
    
    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid UpdateMeetingDTO data){
        log.log(Level.INFO, "UPDATE IN: {0}", data.toString());

        Meeting meeting = repository.findBySnowflake(data.snowflake());
        
        meeting.update(data);
    }
    
    @DeleteMapping("/{id}")
    @Transactional
    public void deactive(@PathVariable("id") Long id){
        log.log(Level.INFO, "DEACTIVE IN: {0}",id);
        Meeting meeting = repository.getReferenceById(id);
        meeting.deactivate();
    }
}
    