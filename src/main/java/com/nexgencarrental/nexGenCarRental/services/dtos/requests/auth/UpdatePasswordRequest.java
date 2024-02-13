package com.nexgencarrental.nexGenCarRental.services.dtos.requests.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePasswordRequest {

    @NotBlank(message = "E-posta boş olamaz")
    @Email(message = "Geçersiz e-posta formatı")
    private String email;

    @NotBlank(message = "Eski şifre boş olamaz")
    private String oldPassword;

    @NotBlank(message = "Yeni şifre boş olamaz")
    private String newPassword;
}
