package com.rainbow.ai.locator;

import com.rainbow.ai.clients.OllamaClient;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Extracted AI prompt/response parsing logic for suggesting CSS selectors.
 * This class contains only AI/prompt logic and does not depend on Selenium/WebDriver.
 */
public class SelectorSuggester {

    /**
     * Ask the provided OllamaClient to suggest a single CSS selector for the given HTML and intent.
     * Returns Optional.empty() if the response doesn't look like a valid selector.
     */
    public static Optional<String> suggestCss(OllamaClient client, String html, String userIntent) throws Exception {
        String prompt = """
            You are a selector generator. Given the following HTML snapshot, return one single CSS selector
            (and nothing else) that targets the element matching this intent: "%s".
            Constraints:
            - Prefer stable attributes (id, name, aria-label, role, data-*)
            - Avoid brittle nth-child unless needed
            - Return ONLY the CSS selector, no explanation.

            HTML (truncated allowed):
            %s
            """.formatted(userIntent, truncate(html, 5000));

        String raw = client.ask(prompt).trim();

        String css = raw
                .replace("`", "")
                .replace("\"", "")
                .replace("CSS:", "")
                .trim();

        css = css.split("\\R")[0].trim();

        Pattern p = Pattern.compile("^[A-Za-z0-9#\\[\\].= _\\-:'>\\*\\+\\^\\$\\(\\)]+$");
        Matcher m = p.matcher(css);

        return m.find() ? Optional.of(css) : Optional.empty();
    }

    private static String truncate(String s, int max) {
        if (s == null) return "";
        return s.length() <= max ? s : s.substring(0, max);
    }
}

