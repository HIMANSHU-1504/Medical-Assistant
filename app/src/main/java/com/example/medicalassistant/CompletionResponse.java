package com.example.medicalassistant;

import java.util.List;

public class CompletionResponse {
    private List<Choice> choices;
    // Add other properties as needed

    public List<Choice> getChoices() {
        return choices;
    }

    public class Choice {
        private String text;
        private double confidence;
        // Add other properties as needed

        public String getText() {
            return text;
        }
    }
}

