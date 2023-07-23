package io.winty.struct.meetingservice;

import io.winty.struct.meetingservice.dto.CreateMeetingDTO;
import io.winty.struct.meetingservice.dto.UpdateMeetingDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "meeting")
@Entity(name="Meeting")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Meeting {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String snowflake;
    private String state;
    private Boolean active;
    
    public Meeting(CreateMeetingDTO data){
        active = true;
        name = data.name();
        snowflake = data.snowflake();
        state = data.state();
    }

    public void update(UpdateMeetingDTO data) {
        if ( data.name() != null){
            name = data.name();
        }
        
        if ( data.state() != null){
            state = data.state();
        }

        active = true;
    }
    
    public void deactivate() {
        active = false;
    }

    
}
