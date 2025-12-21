package com.rainbow.ai.locator;

import com.rainbow.ai.clients.OllamaClient;
import org.openqa.selenium.By;
import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Public helper in ai module that allows callers to attempt find-or-heal using an OllamaClient.
 * Enhanced with sanitization and repair logic so it can replace UI-local copy.
 */
public class AiLocatorHealer {

    private final OllamaClient ai;

    public AiLocatorHealer(OllamaClient ai) {
        this.ai = ai;
    }

    public WebElement findOrHeal(WebDriver driver, By primaryLocator, String userIntent) {
        try {
            return driver.findElement(primaryLocator);
        } catch (NoSuchElementException e) {
            try {
                String html = driver.getPageSource();
                // ask AI (may return multiple lines / suggestions)
                String raw = ai.ask(buildPrompt(userIntent, truncate(html, 5000))).trim();
                List<String> candidates = extractCandidates(raw);

                for (String candidate : candidates) {
                    String css = sanitize(candidate);
                    try {
                        List<WebElement> found = tryFind(driver, css);
                        if (found != null && !found.isEmpty()) return found.get(0);
                    } catch (InvalidSelectorException ise) {
                        String repaired = repairAttributeQuoting(css);
                        try {
                            List<WebElement> found2 = tryFind(driver, repaired);
                            if (found2 != null && !found2.isEmpty()) return found2.get(0);
                        } catch (Exception ignored) {}
                    }
                }

                throw new NoSuchElementException("AI could not suggest a locator for: " + userIntent + " (candidates: " + candidates + ")");

            } catch (Exception ex) {
                if (ex instanceof NoSuchElementException) throw (NoSuchElementException) ex;
                throw new RuntimeException("AI healing failed", ex);
            }
        }
    }

    private String buildPrompt(String userIntent, String html) {
        return "You are a selector generator. Given the following HTML snapshot, return one single CSS selector (and nothing else) that targets the element matching this intent: \"" + userIntent + "\".\n\nHTML (truncated allowed):\n" + html;
    }

    private List<String> extractCandidates(String raw) {
        List<String> out = new ArrayList<>();
        if (raw == null || raw.isEmpty()) return out;
        String[] lines = raw.split("\\R");
        for (String l : lines) {
            String s = l.trim();
            if (s.isEmpty()) continue;
            s = s.replaceAll("(?i)^css:\\s*", "");
            s = s.replaceAll("^`+|`+$", "");
            if (s.contains(",")) {
                for (String part : s.split(",")) {
                    if (!part.trim().isEmpty()) out.add(part.trim());
                }
            } else {
                out.add(s);
            }
        }
        return out;
    }

    private String sanitize(String s) {
        if (s == null) return "";
        s = s.trim();
        if ((s.startsWith("\"") && s.endsWith("\"")) || (s.startsWith("'") && s.endsWith("'"))) {
            s = s.substring(1, s.length()-1).trim();
        }
        s = s.replace("`", "");
        return s;
    }

    private List<WebElement> tryFind(WebDriver driver, String css) {
        try {
            return driver.findElements(By.cssSelector(css));
        } catch (InvalidSelectorException ise) {
            throw ise;
        } catch (Exception ex) {
            return new ArrayList<>();
        }
    }

    // Repair attribute selectors missing quotes: e.g. [aria-label=Search Wikipedia] -> [aria-label="Search Wikipedia"]
    private String repairAttributeQuoting(String css) {
        try {
            Pattern p = Pattern.compile("\\[\\s*([^=\\]]+)\\s*=\\s*([^\\]]+)\\s*\\]");
            Matcher m = p.matcher(css);
            StringBuffer sb = new StringBuffer();
            boolean found = false;
            while (m.find()) {
                String attr = m.group(1);
                String val = m.group(2).trim();
                if ((val.startsWith("\"") && val.endsWith("\"")) || (val.startsWith("'") && val.endsWith("'"))) {
                    String rep = "[" + attr + "=" + val + "]";
                    m.appendReplacement(sb, Matcher.quoteReplacement(rep));
                } else {
                    String vEsc = val.replace("\"", "\\\"");
                    String rep = "[" + attr + "=\"" + vEsc + "\"]";
                    m.appendReplacement(sb, Matcher.quoteReplacement(rep));
                    found = true;
                }
            }
            m.appendTail(sb);
            String res = sb.toString();
            return found ? res : css;
        } catch (Exception ex) {
            return css;
        }
    }

    private static String truncate(String s, int max) {
        if (s == null) return "";
        return s.length() <= max ? s : s.substring(0, max);
    }
}
