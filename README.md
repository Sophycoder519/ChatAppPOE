# Chat App – Part 1 & Part 2

## Part 1: Registration and Login
- Username validation (underscore, max 5 characters)
- Password complexity (≥8 chars, uppercase, digit, special character)
- South African cell phone number (+27 followed by 9 digits)
- Login with stored credentials

## Part 2: Sending Messages
- After login, a menu appears:
  1. Send Messages
  2. Show recently sent messages (Coming Soon)
  3. Quit
- User defines number of messages; a for‑loop handles each.
- For each message:
  - Recipient validated (+27...)
  - Message text ≤250 characters
  - Auto‑generated 10‑digit Message ID
  - Auto‑generated Message Hash (format: `00:0:HITHANKS`)
  - User chooses Send, Disregard, or Store
- Sent/Stored messages saved to `messages.json` using `org.json` library
- Total number of sent messages displayed at the end

## How to run
Run `Main.java` in IntelliJ. Register, then log in. After successful login, use the menu.

## Run tests
- `LoginTest.java` – tests for Part 1
- `MessageTest.java` – tests for Part 2 (message length, recipient, hash generation)

## Continuous Integration
GitHub Actions automatically runs all tests on every push (see `.github/workflows/maven.yml`).

## JSON library attribution
https://github.com/stleary/JSON-java