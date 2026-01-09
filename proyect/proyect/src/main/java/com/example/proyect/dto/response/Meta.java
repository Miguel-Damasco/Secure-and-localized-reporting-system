package com.example.proyect.dto.response;

import java.time.Instant;

public record Meta(
        boolean success,
        int statusCode,
        String message,
        String path,
        Instant timestamp
) {
    
    public static Meta of(boolean pSuccess, int pStatusCode, String pMessage, String pPath) {
        return new Meta(pSuccess, pStatusCode, pMessage, pPath, Instant.now());
    }
}
