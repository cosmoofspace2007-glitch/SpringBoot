package Baiss6.model;

import Baiss6.Enum.CarType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table
public class Vehicle
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @Column(name = "license_plate")
    private String licensePlate;
    private String color;
    private CarType carType;

    @OneToMany(mappedBy = "vehicle")
    private List<ParkingTicket> parkingTickets;
}
