package com.giacomelli.JJWT.Model.Dto;

import jakarta.validation.constraints.NotBlank;

public record AuthLoginRequestDto(@NotBlank String username,
                                  @NotBlank String password) {}
