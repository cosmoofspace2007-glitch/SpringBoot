package Baiss6.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class TicketSummaryResponse {
    private int id;
    private String licensePlate;
    private String zoneName;
    private LocalDateTime checkInTime;
    private LocalDateTime checkOutTime;
}

