# Chat App – Part 1: Registration and Login

## Description
Console application that allows a user to register with:
- Username (must contain `_` and be ≤5 characters)
- Password (≥8 chars, one uppercase, one digit, one special character)
- South African cell phone number (must start with `+27` and have 9 digits)

After registration, the user can log in with the same credentials.

## How to run
1. Open the project in IntelliJ.
2. Right-click `Main.java` → Run.
3. Follow the prompts in the console.

## How to run tests
Right-click `LoginTest.java` → Run. All tests should pass.

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