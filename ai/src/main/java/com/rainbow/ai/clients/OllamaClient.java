// Added new canonical OllamaClient implementation under ai module
package com.rainbow.ai.clients;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import okhttp3.*;

import java.nio.charset.StandardCharsets;

public class OllamaClient {

    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final MediaType JSON = MediaType.parse("application/json");
    private static final String OLLAMA_URL =
            System.getenv().getOrDefault("OLLAMA_URL", "http://127.0.0.1:11434/api/generate");

    private final OkHttpClient http;
    private final String model;

    public OllamaClient(String model) {
        this.model = model;
        this.http = new OkHttpClient();
    }

    public String ask(String prompt) throws Exception {
        ObjectNode root = MAPPER.createObjectNode();
        root.put("model", model);
        root.put("prompt", prompt);
        root.put("stream", false);

        String bodyJson = MAPPER.writeValueAsString(root);
        RequestBody body = RequestBody.create(bodyJson.getBytes(StandardCharsets.UTF_8), JSON);

        Request request = new Request.Builder()
                .url(OLLAMA_URL)
                .post(body)
                .build();

        System.out.println("[AI→Ollama] " + bodyJson);

        try (Response resp = http.newCall(request).execute()) {
            String json = resp.body() != null ? resp.body().string() : "";
            System.out.println("[Ollama→AI] " + json);

            if (!resp.isSuccessful()) {
                throw new RuntimeException("Ollama HTTP " + resp.code() + " – body: " + json);
            }

            JsonNode n = MAPPER.readTree(json);
            if (n.has("error")) {
                throw new RuntimeException("Ollama error: " + n.get("error").asText());
            }
            return n.has("response") ? n.get("response").asText().trim() : json.trim();
        }
    }
}

