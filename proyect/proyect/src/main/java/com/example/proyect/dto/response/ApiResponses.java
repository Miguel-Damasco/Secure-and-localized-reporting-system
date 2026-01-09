package com.example.proyect.dto.response;


public final class ApiResponses {

    public static <T> ApiResponse<T> success(T data, int pStatusCode, String pMessage, String pPath) {

        return new ApiResponse.Success<T>(data, 
                                            Meta.of(true, 
                                                    pStatusCode, 
                                                    pMessage, 
                                                    pPath));
    }

    public static <T> ApiResponse<Void> error(String pDetails, String pCode, int pStatusCode, String pMessage, String pPath) {

        ErrorInfo errorInfo = new ErrorInfo(pDetails, pCode);

        return new ApiResponse.Error(errorInfo, Meta.of(false, 
                                                            pStatusCode, 
                                                            pMessage, 
                                                            pPath));
    }

    private ApiResponses() {}
}
