package Baiss4.service;

import Baiss4.request.InstructorCreateRequest;
import Baiss4.model.Instructor;
import Baiss4.repository.InstructorRepository;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Service
public class InstructorService
{
    @Autowired
    private InstructorRepository instructorRepository;

    public InstructorService(InstructorRepository instructorRepository)
        {
        this.instructorRepository = instructorRepository;
        }


    public Instructor findbyid(long id)
    {
        return instructorRepository.findById(id).orElseThrow(() -> new RuntimeException("Instructor not found"));
    }

    public List<Instructor> findAll()
    {
        return instructorRepository.findAll();
    }

    public Instructor createInstructor(InstructorCreateRequest ICR)
    {
        Instructor instructor1 = new Instructor();
        instructor1.setName(ICR.getName());
        instructor1.setEmail(ICR.getEmail());

        return instructorRepository.save(instructor1);
    }
}
