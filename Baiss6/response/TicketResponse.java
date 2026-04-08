package Baiss6.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class TicketResponse {
    private Long id;
    private String licensePlate;
    private String zoneName;
    private LocalDateTime checkInTime;
    private LocalDateTime checkOutTime;
}
