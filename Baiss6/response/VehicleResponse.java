package Baiss6.response;

import Baiss6.Enum.CarType;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VehicleResponse
{
    private int id;
    private String licensePlate;
    private String color;
    private CarType vehicleType;
}
