package Baiss7;

import jakarta.validation.constraints.*;
import jakarta.validation.constraints.Min;

import jakarta.validation.constraints.*;

public class CandidateCreateDto {

    @NotBlank(message = "Full name không được để trống")
    @Size(min = 5, max = 50, message = "Full name phải từ 5-50 ký tự")
    private String fullName;

    @NotBlank(message = "Email không được để trống")
    @Email(message = "Email không đúng định dạng")
    private String email;

    @Min(value = 18, message = "Tuổi phải >= 18")
    private Integer age;

    @Min(value = 0, message = "Kinh nghiệm phải >= 0")
    private Integer yearsOfExperience;

    // 👉 THÊM MỚI
    @NotBlank(message = "Số điện thoại không được để trống")
    @Pattern(
            regexp = "^(03|05|07|08|09)\\d{8}$",
            message = "Số điện thoại không hợp lệ"
    )
    private String phone;

    // Getter & Setter
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }

    public Integer getYearsOfExperience() { return yearsOfExperience; }
    public void setYearsOfExperience(Integer yearsOfExperience) { this.yearsOfExperience = yearsOfExperience; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
}