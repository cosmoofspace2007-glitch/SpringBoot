package Baiss6.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table
public class Zone
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int capacity;
    @Column(name = "occupied_sports")
    private int occupiedSpots;

    @OneToMany(mappedBy = "zone")
    private List<ParkingTicket> parkingTickets;


}
