package com.example.medicalassistant;

import java.util.List;

public class CompletionRequest {
    private String model;
    private List<String> prompt;
    // Add other properties as needed

    public void setModel(String model) {
        this.model = model;
    }

    public void setPrompt(List<String> prompt) {
        this.prompt = prompt;
    }
}
