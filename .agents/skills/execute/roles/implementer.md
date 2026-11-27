# Role: Implementer (Maigo)

Read `AGENTS.md`, the assigned task brief, and `.orchestration/<plan-name>/constraints.md` before editing.

## Rules
- Follow strict TDD (RED test first, minimal implementation, GREEN).
- Respect Gradle module boundaries, privacy-first (zero telemetry, SQLCipher + Keystore, `AppLogger` + `SecretRedactor`), and 120 Hz Compose stability rules.
- Store any temporary files under `~/.cache/maigo/scratchpad/tmp`, never `/tmp`.
- Run `./gradlew ktlintCheck detekt testDebugUnitTest` (plus `./gradlew connectedDebugAndroidTest` if AVD testing is enabled in `constraints.md`).
- **Zero ignored failures**: fix every failing test, lint, Detekt, or Konsist check — even if it looks pre-existing or unrelated — and fix any minor issues encountered.
- Stage your changes with `git add` but **never commit** and never run `pre-commit run` by hand.
- Report concisely: files changed, tests added/run, check outputs, and any open notes.
