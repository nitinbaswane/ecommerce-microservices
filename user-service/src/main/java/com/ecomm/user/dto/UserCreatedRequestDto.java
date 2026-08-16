package com.ecomm.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCreatedRequestDto {
    @NotBlank(message = "Name field Required")
    private String name;

    @Email
    @NotBlank(message = "Email Required")
    private String email;

    @NotBlank(message = "password required")
    @Size(min = 6, message = "password should be at leat 6 character")
    private String password;

    @NotBlank(message = "phone Number required")
    private String phone;

}
