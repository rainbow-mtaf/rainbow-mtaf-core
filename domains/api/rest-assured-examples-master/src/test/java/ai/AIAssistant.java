package ai;

import com.rainbow.ai.clients.OllamaClient;

public class AIAssistant {
    private final OllamaClient client = new OllamaClient(
            System.getProperty("OLLAMA_MODEL", "llama3") // ή mistral/llama3
    );

    public String summarizeResponse(String json) {
        String prompt = """
            You are an API testing assistant.
            Summarize the JSON response in 3 concise bullets: key fields, types, anomalies.
            JSON:
            """ + json;
        try { return client.ask(prompt); }
        catch (Exception e) { return "AI summary unavailable: " + e.getMessage(); }
    }

    public String suggestRestAssuredAssertions(String json) {
        String prompt = """
            Given this JSON API response, propose 3-5 Java RestAssured assertions.
            Use Hamcrest matchers when useful. Return ONLY code lines (no prose, no backticks),
            one assertion per line.
            JSON:
            """ + json;
        try { return client.ask(prompt); }
        catch (Exception e) { return "AI suggestions unavailable: " + e.getMessage(); }
    }

    public String classifyError(String bodyOrLog) {
        String prompt = """
            Classify the likely cause for this failed API call.
            Categories: AUTH, VALIDATION, SCHEMA_DRIFT, NETWORK, DATA_DEPENDENCY, OTHER.
            Return: <CATEGORY> — <one-line rationale>.
            INPUT:
            """ + bodyOrLog;
        try { return client.ask(prompt); }
        catch (Exception e) { return "AI diagnosis unavailable: " + e.getMessage(); }
    }
}
