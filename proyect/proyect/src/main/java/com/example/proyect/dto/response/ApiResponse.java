package com.example.proyect.dto.response;

public sealed interface ApiResponse<T> 
                            permits ApiResponse.Success, ApiResponse.Error{
    
    Meta meta();

    public record Success<T>(
        T data,
        Meta meta
    ) implements ApiResponse<T> {}


    public record Error(
        ErrorInfo error,
        Meta meta
    ) implements ApiResponse<Void>{}

}
