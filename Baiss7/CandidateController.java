package Baiss7;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/candidates")
public class CandidateController {

    @Autowired
    private CandidateRepository repository;


    @PostMapping
    public ApiResponse<Candidate> createCandidate(@Valid @RequestBody CandidateCreateDto dto) {

        Candidate candidate = new Candidate();
        candidate.setFullName(dto.getFullName());
        candidate.setEmail(dto.getEmail());
        candidate.setAge(dto.getAge());
        candidate.setYearsOfExperience(dto.getYearsOfExperience());

        Candidate saved = repository.save(candidate);

        return new ApiResponse<>(
                "SUCCESS",
                "Create candidate successfully",
                saved
        );
    }

    @PutMapping("/update/{id}")
    public ApiResponse<Candidate> updateCandidate(
            @PathVariable Integer id,
            @Valid @ModelAttribute CandidateUpdateDTO dto
    )
    {

        Candidate candidate = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Candidate không tồn tại"));

    }
};
