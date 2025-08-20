# 🌈 Rainbow MTAF Core
The Rainbow Modular Test Automation Framework (MTAF)

---

## Overview
Rainbow MTAF (Modular Test Automation Framework) is an **open-source, modular, and extensible** framework for automated testing.  
It is designed to unify different types of testing (UI, API, Performance, Security) into a **single flexible reporting environment**.

Its main goals are to:
- Provide a **ready-to-use template** that open-source projects or enterprises can adopt to quickly bootstrap automation.
- Act as a **parent project**, enabling other repositories to inherit shared modules, configurations, and reports.
- Incorporate **AI-driven capabilities** (e.g., intelligent locator generation, test data creation, anomaly detection).

---

## Key Features
✅ Modular architecture (UI, API, Performance, Security modules)  
✅ Choice of tools (e.g., Selenium or Cypress for UI, Rest Assured for API, Gatling/JMeter for Performance, Nuclei for Security)  
✅ Unified Reporting Hub integrating multiple reporting sources  
✅ Plug-and-play configuration – easy CI/CD integration  
✅ Extensible for future tools and AI components  

---

## Architecture
The core architecture is built on independent modules:

- **UI Module** → Selenium, Cypress, Playwright, Appium  
- **API Module** → Rest Assured, Karate, Postman collections  
- **Performance Module** → Gatling, JMeter  
- **Security Module** → Nuclei, OWASP tools  
- **AI Add-ons** → intelligent test case generation, smart selectors, anomaly detection  
- **Reporting Layer** → consolidated reports (Serenity BDD, Allure, custom dashboards)

---

## Getting Started
1. Clone the repository
   ```bash
   git clone https://github.com/rainbow-mtaf/core.git

---

## Roadmap

🚀 v0.1 – Core setup

## License

Rainbow MTAF is released under the Apache License 2.0 – free to use in both open-source and enterprise projects.

## Contributing

Contributions are welcome! 🎉
See CONTRIBUTING.md
 for guidelines.

Credits

🌈 Created with ❤️ by Varvara Menta
