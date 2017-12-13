package cz.renovahodonin.webstore.api.v1.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ExceptionResponseDto
{

    public ExceptionResponseDto(String errorCode, String errorMessage)
    {
        this(errorCode, errorMessage, new ArrayList<>());
    }

    public ExceptionResponseDto(String errorCode, String errorMessage, List<String> errors)
    {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.errors = errors;
    }

    private String errorCode;
    private String errorMessage;
    List<String> errors;
}