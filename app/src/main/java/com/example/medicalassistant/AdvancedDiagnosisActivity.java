package com.example.medicalassistant;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.google.gson.annotations.SerializedName;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AdvancedDiagnosisActivity extends AppCompatActivity {

    private EditText messageEditText;
    private Button sendButton;
    private TextView responseTextView;
    private String apiKey = "sk-163YsgcYdqpL1hydEplT3BlbkFJsDhDl5Gikj155DhZxaRq"; // Replace with your OpenAI API key
    private String apiUrl = "https://api.openai.com/v1/chat/completions";

    private Gpt3ApiService gpt3ApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_diagnosis);

        // Initialize Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(apiUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Create the Gpt3ApiService
        gpt3ApiService = retrofit.create(Gpt3ApiService.class);

        messageEditText = findViewById(R.id.messageEditText);
        sendButton = findViewById(R.id.sendButton);
        responseTextView = findViewById(R.id.responseTextView);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the user's message
                String userMessage = messageEditText.getText().toString();

                // Create a request
                CompletionRequest request = new CompletionRequest();
                request.setModel("text-davinci-003");
                request.setPrompt(Collections.singletonList(userMessage));

                // Make the API call
                Call<CompletionResponse> call = gpt3ApiService.createCompletion(request);
                call.enqueue(new Callback<CompletionResponse>() {
                    @Override
                    public void onResponse(Call<CompletionResponse> call, Response<CompletionResponse> response) {
                        if (response.isSuccessful()) {
                            // Handle the API response
                            CompletionResponse completionResponse = response.body();
                            if (completionResponse != null && !completionResponse.getChoices().isEmpty()) {
                                String text = completionResponse.getChoices().get(0).getText();
                                responseTextView.setText(text);
                            }
                        } else {
                            // Handle API error
                            responseTextView.setText("Error: " + response.code());
                        }
                    }

                    @Override
                    public void onFailure(Call<CompletionResponse> call, Throwable t) {
                        // Handle network or other errors
                        responseTextView.setText("Error: " + t.getMessage());
                    }
                });
            }
        });
    }
}
