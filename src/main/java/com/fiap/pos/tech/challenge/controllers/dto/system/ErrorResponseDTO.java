package com.fiap.pos.tech.challenge.controllers.dto.system;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErrorResponseDTO <T> {
    private T message;
    private Integer errorCode;
}
