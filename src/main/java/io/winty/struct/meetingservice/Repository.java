package io.winty.struct.meetingservice;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Repository extends JpaRepository<Meeting, Long> {
    Page<Meeting> findAllByActiveTrue(Pageable page);
    
    Page<Meeting> findAllByActiveTrueAndState(Pageable page, String state);
    
    Meeting findBySnowflake(String snowflake);
}
