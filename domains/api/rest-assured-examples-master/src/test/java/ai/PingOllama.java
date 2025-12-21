package ai;

import com.rainbow.ai.clients.OllamaClient;

public class PingOllama {
    public static void main(String[] args) throws Exception {
        // Δημιουργούμε έναν client που συνδέεται στο τοπικό Ollama
        OllamaClient ai = new OllamaClient("llama3"); // ή "qwen2.5-coder" αν έχεις άλλο μοντέλο

        System.out.println("🟣 Στέλνω δοκιμαστικό prompt στο Ollama...");
        String reply = ai.ask("Say 'OK Varvara, I’m connected to you.'");

        System.out.println("🟢 Απάντηση από Ollama:");
        System.out.println(reply);
    }
}
