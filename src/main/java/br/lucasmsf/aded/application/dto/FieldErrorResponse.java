package br.lucasmsf.aded.application.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class FieldErrorResponse extends MessageResponse {
    private String field;
}
