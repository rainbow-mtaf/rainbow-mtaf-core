# rainbow-mtaf-orchestrator

This module is the **entry point** of Rainbow MTAF.

## Purpose
It orchestrates the full test lifecycle:
1. Executes domain tests
2. Collects raw results
3. Normalizes results into semantic models
4. Invokes AI augmentation
5. Enforces human validation
6. Triggers reporting and observability

## Responsibilities
- Workflow control
- Tool execution (as external processes)
- Human-in-the-loop enforcement
- Release-level coordination

## What belongs here
- Pipeline steps
- Tool adapters (execution & parsing only)
- Human validation logic
- Configuration (release.yaml)
- Calls to AI and observability layers

## What must NOT be here
- AI reasoning logic
- Tool-specific test code
- Visualization logic

## Dependencies
- Depends on `core`
- Depends on `ai`
- Depends on `observability`

This module is the **only place** where all layers are connected.
