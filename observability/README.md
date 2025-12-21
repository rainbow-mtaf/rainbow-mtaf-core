# rainbow-mtaf-observability

This module provides **operational observability** for Rainbow MTAF.

## Purpose
To expose high-level, cross-domain test execution metrics for:
- release readiness
- risk visibility
- management reporting

It is designed for **monitoring and insight**, not for automated decisions.

## What belongs here
- InfluxDB writers
- Common metrics schema
- Grafana dashboard definitions
- Release-level summary generation

## What must NOT be here
- AI logic
- Test execution
- Detailed test reports
- Blocking or decision logic

## Dependencies
- Depends on `core`

This layer focuses on:
- status
- trends
- blockers
- timing

Detailed test analysis remains in domain-specific reports.
