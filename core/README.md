# rainbow-mtaf-core

This module defines the **semantic core** of the Rainbow MTAF.

## Purpose
It provides a common, tool-agnostic language for describing:
- test failures
- regressions
- findings
- risks
- severities

All other modules depend on this layer to ensure consistent meaning across
different tools and domains.

## What belongs here
- Domain contracts (UI, API, Performance, Security)
- Enums (severity, failure type, domain)
- Shared semantic DTOs
- JSON/YAML schemas

## What must NOT be here
- Tool-specific logic (Selenium, Playwright, JMeter, etc.)
- AI logic
- Execution logic
- Reporting or visualization code

## Dependencies
- No dependencies on other Rainbow modules

This module should remain **stable and minimal**, as it is the foundation of the framework.
