# 🔍 Smart Research Assistant

A powerful Smart Research Assistant that utilizes Google’s Gemini API to generate summaries from selected text on any webpage. Built with Spring Boot for backend processing and a Chrome Extension for seamless front-end interaction.

---

## ✨ Features

- 🚀 **Text Summarization**: Select any text on a webpage and click the "Summarize" button to get a concise summary.
- 🧠 **AI-Powered**: Uses Google Gemini API for accurate and intelligent summaries.
- 🧩 **Chrome Extension**: Right-click or click the extension icon to interact with the assistant directly in the browser.
- 📋 **Copy Summary**: Copy summarized text with one click.
- 🔀 **Multi-Tab Support**: Summaries are shown even after switching tabs.
- 🔒 **Secure Config**: API key and endpoint are configured in runtime environment (not hardcoded in source files).

---

## 🛠️ Tech Stack

Layer         | Technology

Backend       | Java, Spring Boot

AI Integration| Gemini API (Google AI Studio)

Frontend      | Chrome Extension (HTML/CSS/JS)

Build Tool    | Maven

---

## 📁 Project Structure

```bash
research-assistant/
├── src/
│   ├── /main/java/com/researchAssistant/
│   ├── controller/
│   ├── Entity/
│   ├── Helper/
|   ├── Service/
│   └── ResearchAssistantApplication.java
|   └── Resource/
├── README.md
└── pom.xml

ResearchAssistant-ext
├── extension/
│   ├── manifest.json
│   ├── popup.html
│   ├── sidepanel.js
│   ├── sidepanel.css
|   ├── background.js

```
---

### ⚙️ Environment Setup

✅ Prerequisites
Java 17+

Maven

Gemini API Key (from Google AI Studio)

Chrome Browser

### 🚀 Backend Setup


1. Clone the Repository
```bash
git clone https://github.com/yourusername/smart-research-assistant.git
cd smart-research-assistant/backend
```
2. Configure API Key

⚠️ DO NOT hardcode your API key or endpoint in application.properties.

Instead, pass them as environment variables or using your IDE's Run Configuration:

GEMINI_API_KEY \
GEMINI_API_URL

3. Build and Run
```bash
mvn clean install
mvn spring-boot:run
```
### 🧩 Chrome Extension Setup
Go to chrome://extensions/

Enable Developer Mode

Click Load unpacked

Select the /ResearchAssistant-ext folder inside the project

Pin the extension to your toolbar

Use it by selecting text and clicking the extension icon or context menu

### 🔐 Security Notes
API keys are never exposed in client-side JavaScript.

Use secure storage and runtime configuration to inject keys.

Backend acts as a proxy to the Gemini API to avoid exposing credentials.

### 📌 Example Usage
Highlight a paragraph on any webpage.

Click the extension icon → Press Summarize.

Summary is shown instantly with a Copy button.

### 🧱 Future Enhancements
✨ Support for multiple languages

📚 History of previous summaries

🖼️ Screenshot + summarize

💬 Voice command integration

🧾 PDF/Doc summarization

### 🙋‍♂️ Author
Developed by Deven Patel \
Let’s connect and collaborate!