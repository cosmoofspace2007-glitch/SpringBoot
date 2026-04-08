package Baiss6.repository;

import Baiss6.model.Vehicle;
import Baiss6.response.VehicleResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long>
{
       @Query
       (
        """
        select new com.example.dto.VehicleResponse(v.id, v.licensePlate, v.color, v.carType) from Vehicle v where (:keyword IS NULL OR
                                               LOWER(v.id) LIKE LOWER(CONCAT('%', :keyword, '%')) OR
                                               LOWER(v.licensePlate) LIKE LOWER(CONCAT('%', :keyword, '%')) OR
                                               LOWER(v.color) LIKE LOWER(CONCAT('%', :keyword, '%')) OR
                                               LOWER(v.carType) LIKE LOWER(CONCAT('%', :keyword, '%')))                
                         
        """)
       Page<VehicleResponse> findAllByKeyword(@Param("keyword") String keyword, Pageable pageable);
}
