package com.newSpringProject.demo.newSpringProject.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddStudentRequestDto {
    private Long id;

    @NotBlank(message = "Name is Required!")
    @Size(min = 3, max = 30, message = "Name should be of 3 to 30 characters only!")
    private String name;

    @Email
    @NotBlank(message = "Email is Required!")
    private String email;
}
