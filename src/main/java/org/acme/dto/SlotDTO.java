package org.acme.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class SlotDTO {
    private long did;
    private LocalDate date;
}
