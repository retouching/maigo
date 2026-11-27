# Local Agent Workflow

Feature changes follow `sketch` → `plan` → `execute`:
1. `sketch` (`.agents/skills/sketch/SKILL.md`): clarifies requirements one question at a time and writes a French spec to `~/.cache/maigo/scratchpad/specs/<YYYY-MM-DD>-<subject>.md`.
2. `plan` (`.agents/skills/plan/SKILL.md`): turns the validated spec into small TDD tasks in `~/.cache/maigo/scratchpad/plans/<YYYY-MM-DD>-<subject>.md`, including an explicit AVD vs JVM test section.
3. `execute` (`.agents/skills/execute/SKILL.md`):
   - First checks `adb devices` and `emulator -list-avds` and asks the owner whether to run integration/E2E tests on an AVD.
   - Creates `.orchestration/<plan-name>/` (gitignored) with `progress.md`, `constraints.md`, task briefs (`task-brief`), and review diffs (`review-package`).
   - Runs `implementer` → `task-reviewer` → fix loop with `fix-reviewer` per task, commits after review, then runs `branch-reviewer` at the end.

## Agent Tier Mapping

| Role | Tier | Antigravity | Claude Code | Codex |
|---|---|---|---|---|
| `implementer` | `standard` | `inherit` | `sonnet` | `medium` |
| `task-reviewer` | `standard` | `inherit` | `sonnet` | `medium` |
| `fix-reviewer` | `fast` | `flash` | `haiku` | `low` |
| `branch-reviewer` | `strong` | `pro` | `opus` | `high` |
