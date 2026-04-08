package Baiss6.service;

import Baiss6.model.TicketRequest;
import Baiss6.response.TicketResponse;
import Baiss6.response.TicketSummaryResponse;

import java.util.List;

public interface ParkingService {
    TicketResponse checkIn(TicketRequest request);
    TicketResponse checkOut(Long vehicleId);
    List<TicketSummaryResponse> getTodayTickets();
}
