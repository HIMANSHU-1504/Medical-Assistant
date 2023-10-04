package com.example.medicalassistant;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface Gpt3ApiService {
    @Headers({
            "Content-Type: application/json",
            "Authorization: Bearer YOUR-API-KEY"
    })
    @POST("completions")
    Call<CompletionResponse> createCompletion(@Body CompletionRequest request);
}