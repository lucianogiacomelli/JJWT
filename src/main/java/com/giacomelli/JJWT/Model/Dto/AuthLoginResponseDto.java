package com.giacomelli.JJWT.Model.Dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"username","message","jwt","status"})
public record AuthLoginResponseDto (String username,
                                    String message,
                                    String jwt,
                                    Boolean status) {}
