# claude-test-demo

A demo Java project showing how Claude AI automatically generates and runs JUnit 5 tests via GitHub Actions.

---

## How It Works

1. You open a Pull Request (or comment `@claude` on an existing one)
2. GitHub Actions triggers the Claude workflow
3. Claude reads the source code and `CLAUDE.md` conventions
4. Claude generates missing test files in `src/test/java/com/demo/`
5. Claude runs `mvn test` to verify everything passes
6. Results are posted as a PR comment

---

## Setup (one-time)

### 1. Get an Anthropic API Key
- Go to https://console.anthropic.com
- Sign up and create an API key

### 2. Add the API Key to GitHub Secrets
- Open your repository on GitHub
- Go to **Settings → Secrets and variables → Actions**
- Click **New repository secret**
- Name: `ANTHROPIC_API_KEY`
- Value: your API key from step 1
- Click **Add secret**

### 3. Push This Code to GitHub
```bash
git init
git add .
git commit -m "Initial commit: demo project for Claude test generation"
git branch -M main
git remote add origin https://github.com/YOUR_USERNAME/claude-test-demo.git
git push -u origin main
```

---

## Try the Demo

### Option A — Open a Pull Request
1. Create a new branch: `git checkout -b add-new-feature`
2. Add a new Java class to `src/main/java/com/demo/` (no tests needed)
3. Push and open a PR
4. Watch the **Claude Test Generator** action run automatically

### Option B — Trigger via Comment
1. Open any Pull Request
2. Add a comment: `@claude generate tests for all classes`
3. The workflow triggers immediately

---

## Project Structure

```
claude-test-demo/
├── .github/
│   └── workflows/
│       └── claude-tests.yml      ← GitHub Actions workflow
├── src/
│   ├── main/java/com/demo/
│   │   ├── Calculator.java       ← math operations
│   │   ├── StringUtils.java      ← string helpers
│   │   └── OrderProcessor.java   ← order pricing logic
│   └── test/java/com/demo/       ← Claude will generate tests here
├── CLAUDE.md                     ← instructions Claude follows
├── pom.xml                       ← Maven build config
└── README.md
```
