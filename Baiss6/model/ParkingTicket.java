package Baiss6.model;

import Baiss6.Enum.CarType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table
@Entity
public class ParkingTicket
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "check_in_time")
    private LocalDateTime checkInTime;
    @Column(name = "check_out_time")
    private LocalDateTime checkOutTime;
    private CarType carType;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    @ManyToOne
    @JoinColumn(name = "zone_id")
    private Zone zone;
}
