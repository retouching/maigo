---
name: sketch
description: >-
  Turn an idea of the owner into a validated spec for maigo, one question at a
  time, before any plan or code. Use when the owner wants to add, change or
  remove a feature, or says "esquisse", "sketch", "spec" or "design".
---

# Sketch (Maigo)

1. **Understand**: read relevant modules and `docs/agents/*.md`. Ask one question per message with clear options and a recommendation. Stop when every section can be written without guessing.
2. **Write the spec**: write in French to `~/.cache/maigo/scratchpad/specs/<YYYY-MM-DD>-<subject>.md` (never inside the repository). Include sections:
   - Intention
   - Objectifs / Non-objectifs / Anti-objectifs
   - Ce qui reste / Ce qui part / Ce qui arrive
   - Comportement hors connexion, performance & vie privée
   - Données & migrations Room (si applicable)
   - Écrans & états UI
   - Documents et fichiers à mettre à jour
   Mark owner decisions `[owner]` and proposals `[proposé]`.
3. **Validate**: resolve every `[proposé]` point one question at a time, then propose moving to `plan`.
