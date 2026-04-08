package Baiss6.service;

import Baiss6.repository.VehicleRepository;
import Baiss6.response.VehicleResponse;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@Getter
@Setter
public class VehicleService
{
    private final VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public Page<VehicleResponse> getPagedVehicles(int page, int size, String sortBy, String direction, String keyword)
    {
        if (page < 0)
        {
            page = 0;
        }

        Sort sort = Sort.unsorted();

        if(sortBy != null && !sortBy.isEmpty() && direction != null && !direction.isEmpty())
        {
            if(direction.equalsIgnoreCase("ASC"))
            {
                sort = Sort.by(sortBy).ascending();
            }
            else if(direction.equalsIgnoreCase("DESC"))
            {
                sort = Sort.by(sortBy).descending();
            }
        }
        Pageable pageable = PageRequest.of(page, size, sort);
        return VehicleRepository.findAllByKeyword(keyword,pageable);
    }
}
