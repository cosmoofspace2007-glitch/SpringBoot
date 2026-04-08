package Baiss6.controller;

import Baiss6.response.VehicleResponse;
import Baiss6.service.VehicleService;
import Baiss6.response.ApiResponse;
import Baiss6.response.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/vehicles")
@RequiredArgsConstructor
public class VehicleController {

    private final VehicleService vehicleService;

    // ===================== POST =====================
    @PostMapping
    public ApiResponse<VehicleResponse> createVehicle(@RequestBody VehicleRequest request) {

        VehicleResponse response = vehicleService.createVehicle(request);

        return ApiResponse.<VehicleResponse>builder()
                .code(1000)
                .message("Create vehicle successfully")
                .result(response)
                .build();
    }

    // ===================== GET (PAGING + SORT + SEARCH) =====================
    @GetMapping
    public ApiResponse<PageResponse<VehicleResponse>> getVehicles(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String direction,
            @RequestParam(required = false) String keyword
    ) {

        // Gọi service
        Page<VehicleResponse> pageData =
                vehicleService.getPagedVehicles(page, size, sortBy, direction, keyword);

        // Convert Page -> PageResponse
        PageResponse<VehicleResponse> pageResponse = PageResponse.<VehicleResponse>builder()
                .content(pageData.getContent())
                .page(pageData.getNumber())
                .size(pageData.getSize())
                .totalElements(pageData.getTotalElements())
                .totalPages(pageData.getTotalPages())
                .build();

        return ApiResponse.<PageResponse<VehicleResponse>>builder()
                .code(1000)
                .message("Get vehicles successfully")
                .result(pageResponse)
                .build();
    }
}

