package com.qengine.app.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 *
 * @author sergey
 */
@Data
public class RegistrationDto {
    @NotBlank(message = "Логин обязателен")
    private String username;
    
    @NotBlank(message = "Пароль обязателен")
    @Size(min = 6, message = "Пароль должен быть не менее 6 символов")
    private String password;
    
    @Email(message = "Некорректный email")
    private String email;
}
