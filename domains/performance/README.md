# Performance Testing Module

## Overview
This module contains the **performance testing layer** of the unified test framework.
Its purpose is **not load testing for capacity planning**, but **controlled performance comparison**
between test runs to support release decisions.

The focus is on:
- detecting regressions
- comparing trends
- enabling AI-assisted analysis
- enforcing human validation

---

## Scope
The module uses **Apache JMeter** to execute lightweight performance scenarios
against selected API endpoints of the system under test.

### What this module does
- Executes a small number of deterministic performance scenarios
- Produces raw performance results (JTL)
- Feeds results into the AI augmentation layer for **difference analysis**
- Exposes high-level metrics to observability dashboards

### What this module intentionally does NOT do
- Full-scale load or stress testing
- Auto-block releases
- Auto-tune thresholds
- Make autonomous decisions

---

## Test Design Principles
- **Minimal load** (few threads, stable configuration)
- **Repeatability over realism**
- **Comparison over absolutes**

Each test plan is designed to:
- be reproducible
- highlight meaningful deltas
- avoid noise-driven conclusions

---

## Execution
Performance tests are executed via the orchestrator or directly through JMeter in non-GUI mode:

```bash
jmeter -n -t performance.jmx -l results.jtl
