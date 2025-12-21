# rainbow-mtaf-domains

This directory contains **tool-specific test execution modules**.

## Purpose
To execute tests using different tools and produce raw results.

Each domain module is:
- independent
- executable on its own
- unaware of AI, orchestration or observability logic

## Domains included
- UI testing (Selenium, Playwright)
- API testing (Rest Assured)
- Performance testing (JMeter)
- Security testing (Nuclei)

## What belongs here
- Test code
- Tool configurations
- Tool-native reports

## What must NOT be here
- AI logic
- Semantic models
- Orchestrator logic
- Observability writers

## Integration model
Domain modules are:
- NOT Maven dependencies
- Invoked as external processes by the orchestrator

This ensures maximum tool flexibility and minimal coupling.
