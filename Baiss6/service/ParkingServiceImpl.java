package Baiss6.service;

import Baiss6.model.ParkingTicket;
import Baiss6.model.TicketRequest;
import Baiss6.model.Vehicle;
import Baiss6.model.Zone;
import Baiss6.repository.ParkingTicketRepository;
import Baiss6.repository.VehicleRepository;
import Baiss6.repository.ZoneRepository;
import Baiss6.response.TicketResponse;
import Baiss6.response.TicketSummaryResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ParkingServiceImpl implements ParkingService {

    private final VehicleRepository vehicleRepository;
    private final ZoneRepository zoneRepository;
    private final ParkingTicketRepository ticketRepository;

    @Transactional
    @Override
    public TicketResponse checkIn(TicketRequest req) {

        Vehicle vehicle = vehicleRepository.findById(req.getVehicleId())
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));

        Zone zone = zoneRepository.findById(req.getZoneId())
                .orElseThrow(() -> new RuntimeException("Zone not found"));

        if (zone.getOccupiedSpots() >= zone.getCapacity()) {
            throw new RuntimeException("Zone full");
        }

        ParkingTicket ticket = new ParkingTicket();
        ticket.setVehicle(vehicle);
        ticket.setZone(zone);
        ticket.setCheckInTime(LocalDateTime.now());

        zone.setOccupiedSpots(zone.getOccupiedSpots() + 1);

        ticketRepository.save(ticket);
        zoneRepository.save(zone);

        return TicketResponse.builder()
                .id((long) ticket.getId())
                .licensePlate(vehicle.getLicensePlate())
                .zoneName(zone.getName())
                .checkInTime(ticket.getCheckInTime())
                .build();
    }

    @Transactional
    @Override
    public TicketResponse checkOut(Long vehicleId) {

        ParkingTicket ticket = ticketRepository
                .findTopByVehicleIdAndCheckOutTimeIsNullOrderByCheckInTimeDesc(vehicleId)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        ticket.setCheckOutTime(LocalDateTime.now());

        Zone zone = ticket.getZone();
        zone.setOccupiedSpots(zone.getOccupiedSpots() - 1);

        ticketRepository.save(ticket);
        zoneRepository.save(zone);

        return TicketResponse.builder()
                .id((long) ticket.getId())
                .licensePlate(ticket.getVehicle().getLicensePlate())
                .zoneName(zone.getName())
                .checkInTime(ticket.getCheckInTime())
                .checkOutTime(ticket.getCheckOutTime())
                .build();
    }

    @Override
    public List<TicketSummaryResponse> getTodayTickets() {
        return ticketRepository.getTodayTickets();
    }
}
