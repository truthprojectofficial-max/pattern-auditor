# AIDERTESTBOX — Aider Sandbox

> Created 2026-07-24
> Location: C:\AIDERTESTBOX
> Purpose: Aider drafts code, scripts, and research here. You review
> the diff. If good, you take it where it needs to go.

## What this is

A general-purpose Aider workspace. Aider (an AI pair-programmer that
runs on local Ollama) drafts code in this folder. It has its own git
repo, its own config, and its own instruction set. Everything Aider
produces is a draft until you review it and decide.

## What lives here



## The rules

1. **No auto-commit.** Aider suggests changes; you review the diff
   and decide whether to keep it. ()

2. **No fabrication.** If Aider doesn't know something, it says
   "I don't know." If a file doesn't exist, it says so. If a test
   fails, it reports the failure. No confident language hiding
   weak content.

3. **Read before write.** Aider must read a file before editing it.
   Never guess file contents.

4. **No progress in 3 turns = STOP.** If Aider hasn't made a real
   file change in 3 turns, it must say "No progress — I am stuck"
   instead of continuing to talk in circles.

5. **Architect mode.** Aider proposes changes; you approve before
   it writes. (, )

6. **Local only.** Aider runs on local Ollama. No cloud API calls.
   No sending code anywhere. The sandbox stays on this machine.

## How to use it



## What Aider is good for

- Drafting utility scripts (file parsers, data cleaners, formatters)
- Prototyping new functions before integrating them somewhere
- Generating test cases (you review + keep the good ones)
- Refactoring draft code (in this sandbox only, not in your real project)
- Explaining library APIs (if you paste the docs)
- Small jobs: 5 min to 2 hours. If it smells like a 6-month platform,
  push back and carve the smallest valuable slice.

## What Aider is NOT for

- Editing files outside this sandbox (banned — drafts only)
- Making architectural decisions (you decide; Aider drafts)
- Replacing the actual work (the drafts go to you, not the project)
- Cloud operations of any kind
