package Baiss6.repository;

import Baiss6.model.ParkingTicket;
import Baiss6.response.TicketSummaryResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ParkingTicketRepository extends JpaRepository<ParkingTicket, Long> {

    Optional<ParkingTicket>
    findTopByVehicleIdAndCheckOutTimeIsNullOrderByCheckInTimeDesc(Long vehicleId);

    @Query("""
        SELECT new Baiss6.TicketSummaryResponse(
            t.id,
            v.licensePlate,
            z.name,
            t.checkInTime,
            t.checkOutTime
        )
        FROM ParkingTicket t
        JOIN t.vehicle v
        JOIN t.zone z
        WHERE DATE(t.checkInTime) = CURRENT_DATE
    """)
    List<TicketSummaryResponse> getTodayTickets();
}
