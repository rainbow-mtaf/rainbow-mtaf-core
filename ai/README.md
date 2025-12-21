# rainbow-mtaf-ai

This module contains the **AI augmentation layer** of Rainbow MTAF.

## Purpose
To analyze normalized, semantic test data and produce:
- suggestions
- summaries
- difference analyses
- risk signals

AI is used strictly as a **reasoning component**, never as an execution or decision engine.

## What belongs here
- LLM clients (e.g. Ollama)
- Prompt definitions (versioned)
- Domain analyzers (UI, API, Performance, Security)
- AI output models (suggestions, summaries)

## What must NOT be here
- Tool-specific code (Selenium, Playwright, etc.)
- Test execution
- Human approval logic
- CI/CD orchestration

## Dependencies
- Depends on `core`

The AI layer does not know:
- which tools produced the data
- how suggestions will be applied
- whether suggestions are accepted or rejected
