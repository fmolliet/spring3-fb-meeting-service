package io.winty.struct.meetingservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateMeetingDTO( 
    String name,
    @NotNull @NotBlank 
    String snowflake,
    @NotNull @NotBlank 
    @Size(min = 2)
    String state ) {}
