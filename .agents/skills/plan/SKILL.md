---
name: plan
description: >-
  Turn a validated maigo spec into an implementation plan of small tasks, each
  with its modules, files, interfaces, tests and commit, that subagents can
  execute. Use after the sketch skill, or when the owner asks for a plan.
---

# Plan (Maigo)

1. One spec → one plan covering the whole spec; one plan at a time.
2. Write in French to `~/.cache/maigo/scratchpad/plans/<YYYY-MM-DD>-<subject>.md` (never inside the repository).
3. Include English structural headings so `task-brief` parses cleanly:
   - `**Goal:**`, `**Architecture:**`, `**Spec:**`
   - `## Global Constraints`
   - `## AVD & Test Execution Scope` (explicitly listing which integration/E2E tests require an AVD or device via `connectedDebugAndroidTest` vs JVM/Robolectric via `testDebugUnitTest`, noting that `execute` must check `adb devices` / `emulator -list-avds` and ask the owner before starting)
   - `## Review Focus`
   - `### Task <id>: <title>` sections separated by `---`
4. Every task specifies Modules & Files, Interfaces (`Consumes`/`Produces`), test-first steps (RED → implement → GREEN + `./gradlew ktlintCheck detekt testDebugUnitTest`), Room schema/migration verification when entities change, and its Conventional Commit message in English.
5. Final task updates human & agent docs, runs `./gradlew ktlintCheck detekt lintDebug testDebugUnitTest assembleDebug` (plus `assembleRelease` when touching ProGuard/DI/serialization), and `connectedDebugAndroidTest` if approved by the owner for AVD/device testing.
