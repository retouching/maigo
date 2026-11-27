---
name: execute
description: >-
  Execute a maigo plan task by task with subagents, one implementer per task, a
  reviewer after each, every finding fixed, and a review of the whole branch at
  the end. Use when the owner gives a plan and asks to run it (this is the
  owner's explicit request for subagents).
---

# Execute (Maigo)

Runtime-agnostic controller workflow using role names (`implementer`, `task-reviewer`, `fix-reviewer`, `branch-reviewer`) and abstract tiers (`fast`, `standard`, `strong`). Full role instructions live in `.agents/skills/execute/roles/<role>.md`.

## Mandatory Pre-Execution AVD Gate

Before dispatching the first `implementer`:
1. Run `adb devices` and `emulator -list-avds`.
2. **Explicitly ask the owner** whether integration/E2E tests (`./gradlew connectedDebugAndroidTest`) should run on an AVD (naming detected AVDs or reporting none) / connected device, or stay on JVM + Robolectric (`./gradlew testDebugUnitTest`) for this run.
3. Record the owner's answer in `.orchestration/<plan-name>/constraints.md`.

## Execution Loop

1. Initialize `.orchestration/<plan-name>/` (gitignored) with `progress.md` and `constraints.md` (including `~/.cache/maigo/scratchpad/tmp` for temp files, no `Co-Authored-By`, never running hooks by hand, and the owner's AVD decision).
2. For each task `<id>`:
   - Extract brief: `bash .agents/skills/execute/scripts/task-brief <plan> <id> .orchestration/<plan-name>`.
   - Dispatch `implementer` (`standard`, or `strong` for heavy tasks) to implement via TDD, fix every test/check failure (even seemingly unrelated or pre-existing) and every minor issue encountered, stage changes without committing, and write its report.
   - Package diff: `bash .agents/skills/execute/scripts/review-package <base> index .orchestration/<plan-name>`.
   - Dispatch `task-reviewer` (`standard`) on the diff against brief and `AGENTS.md`.
   - Fix every finding (including minor ones) and re-verify with `fix-reviewer` (`fast`), escalating to `strong` after 3 rounds and controller ruling after 5.
   - Verify `git write-tree` matches the reviewed index and commit with the task's Conventional Commit message.
3. After the last task:
   - Run full Definition of Done checks (`./gradlew ktlintCheck detekt lintDebug testDebugUnitTest assembleDebug`, plus `connectedDebugAndroidTest` if approved by the owner for AVD/device testing).
   - Dispatch `branch-reviewer` (`strong`) on `<first-base> HEAD`.
   - Fold fixes with `git commit --fixup=<sha>` + `GIT_SEQUENCE_EDITOR=: git rebase -i --autosquash <first-base>`.
   - Report concisely in French (including which tests ran on JVM/Robolectric vs AVD) without pushing.
