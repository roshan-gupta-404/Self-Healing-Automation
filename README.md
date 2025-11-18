# 🧠 Self-Healing Selenium Framework using Generative AI

This project showcases a **smart, AI-driven test automation framework** built with **Selenium WebDriver**, **OpenAI GPT**, and **Java**. Through dynamic locator extraction and intelligent recovery mechanisms, this framework eliminates hardcoded selectors and enables a self-healing test process.

---

## 🚀 Overview

Static locators often break as modern web applications evolve. To solve this, the framework uses **ChatGPT** to:
- Analyze the current DOM
- Generate locator JSON structures
- Replace or heal broken locators during test execution

This allows the tests to adapt automatically without manual intervention.

---

## ✨ Features

- 🔍 **Dynamic Locator Extraction** using GPT (no hardcoded XPath/CSS)
- 🤖 **AI-powered Self-Healing** for flaky web elements
- 🧩 **JSON-based Locator Mapping** parsed into Selenium `By` objects
- 📄 **Page Object Model (POM)** friendly
- ⚙️ **Built with Selenium, TestNG, and OpenAI API**

---

## 🔧 Tech Stack

- **Java 17+**
- **Selenium WebDriver**
- **TestNG** for test orchestration
- **OpenAI GPT (Chat Completion API)**
- **Dotenv** for secure API key management
- **Jackson** for JSON parsing

---

## 🧰 How It Works

1. Framework initializes OpenAI and WebDriver
2. Sends the current page source to GPT with a prompt to return JSON locators
3. Locators are parsed into a HashMap via `LocatorReader`
4. Page classes use these locators dynamically to perform actions
5. No need to update XPath or CSS manually if UI changes

---

## 📊 Architecture at a Glance

The system follows a sequence:
1. **Setup > Fetch Locators > Parse > Interact > Heal if needed**

A flow diagram is provided in the project for better visualization.

---

## 🛡️ Usage

```bash
# Clone the repo
git clone https://github.com/yourname/self-healing-selenium-ai

# Navigate to the project
cd self-healing-selenium-ai

# Configure your .env with OPENAI_API_KEY

# Run tests
mvn test
