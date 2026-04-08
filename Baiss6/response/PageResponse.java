package Baiss6.response;

import java.util.List;

//PageResponse <T> (  List<T> items, Integer page, Integer size, Long totalItems, Integer totalPages, Boolean isLast )
//VehicleResponse ( Long id, String licensePlate, String color, VehicleType vehicleType )
public class PageResponse<T>
{
    private List<T> items;
    private int page;
    private int size;
    private Long totalItems;
    private int totalPages;
    private Boolean isLast;
}
