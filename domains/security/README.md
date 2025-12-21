
---

# 📄 `security-tests/README.md`

```md
# Security Testing Module

## Overview
This module represents the **security testing layer** of the unified test framework.

It integrates **automated security scanning** as an input source for
AI-assisted analysis and reporting — not as an autonomous security decision engine.

---

## Scope
The module uses **Nuclei** to execute a **limited and curated set of templates**
against the system under test.

### What this module does
- Runs automated security scans
- Collects raw findings
- Passes results to the AI layer for:
  - deduplication
  - clustering
  - summarization
- Produces human-readable security insights

### What this module intentionally does NOT do
- Exploit vulnerabilities
- Perform penetration testing
- Automatically open tickets
- Automatically block releases

---

## Test Design Principles
- **Signal over noise**
- **Limited scope**
- **Interpretability first**

Only a small subset of templates is used to:
- avoid overwhelming results
- demonstrate correlation and summarization
- keep focus on decision support

---

## Execution
Security scans are executed via the orchestrator or directly:

```bash
nuclei -t templates/ -json -o results.json
