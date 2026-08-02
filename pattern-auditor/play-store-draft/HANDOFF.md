# HANDOFF - Pattern AI-uditor Play Store submission

Date: 2026-08-02
Operator: Justin Barnett (TruthProject)
truth.project.official@gmail.com

## Status

| # | Task | Who | Cost | Time | Done |
|---|------|-----|------|------|------|
| 0 | App built, tested, on phone | me | free | done | yes |
| 1 | GitHub Release v0.1.0 published | you | free | 2 min | next |
| 2 | Pick a colour palette (A/B/C) | you | free | 30 sec | no |
| 3 | Privacy policy hosted on a public URL | you | free | 5 min | no |
| 4 | Google Play Console account | you | $25 USD one-off | 15 min | no |
| 5 | Create the app in Play Console | you | free | 30 min | no |
| 6 | Upload the AAB and pick the chosen icon | you | free | 15 min | no |
| 7 | Fill the data-safety form | you | free | 10 min | no (answers in `console-answers/DATA_SAFETY.md`) |
| 8 | Fill the content-rating form | you | free | 5 min | no (answers in `console-answers/CONTENT_RATING.md`) |
| 9 | Paste the store listing copy | you | free | 10 min | no (copy in `store-listing/STORE_LISTING.md`) |
| 10 | Set the privacy policy URL in the listing | you | free | 30 sec | no |
| 11 | Add 2-4 testers to the internal test track | you | free | 5 min | no |
| 12 | Push the v0.1.0 build to internal test | you | free | 5 min | no |
| 13 | Wait for 2 weeks of feedback from friends | you | free | 14 days | no |
| 14 | Address any feedback in v0.1.1 | me | free | 1-3 days | no |
| 15 | Promote internal test to production | you | free | 5 min | no |
| 16 | Wait for Google review (1-7 days) | Google | free | 1-7 days | no |
| 17 | Public listing goes live | Google | free | automatic | no |

Total cost if you do everything: $25 one-off + 2-4 weeks of calendar time.
Total cost if you stop at GitHub Release: $0.

## Step 1: publish the GitHub Release

Open https://github.com/truthprojectofficial-max/pattern-auditor/releases/new in your browser.

- Tag: v0.1.0 (already pushed)
- Title: Pattern AI-uditor v0.1.0
- Description: copy from `dist/RELEASE_NOTES.md`
- Attach: `dist/Pattern-AI-uditor-v0.1.0.apk` and `dist/Pattern-AI-uditor-v0.1.0.aab`
- Publish

## Step 2: pick a palette

Open the four files in `theme/` and the six images in `option-a/`, `option-b/`, `option-c/`.

- Option A: TruthProject default. Navy + cyan + pink. Already in the app.
- Option B: Forensic courtroom. Beige + deep blue + amber. For rebranding toward "court-grade".
- Option C: Minimal mono. Black + white. For maximum portability.

Tell me "A", "B", or "C". I will recolour the launcher icon and the in-app colour scheme if you pick B or C.

## Step 3: host the privacy policy

Two free options:

### Option I: GitHub Pages

1. Create a new branch in this repo: `git checkout --orphan gh-pages && git rm -rf . && git checkout play-store-draft/privacy-policy/index.html && git commit -m "Privacy policy" && git push origin gh-pages`
2. Go to https://github.com/truthprojectofficial-max/pattern-auditor/settings/pages
3. Source: "Deploy from a branch", Branch: `gh-pages`, Folder: `/ (root)`. Save.
4. URL: `https://truthprojectofficial-max.github.io/pattern-auditor/`

### Option II: Cloudflare Pages with a bsafecode.dev subdomain

1. Cloudflare dashboard -> Workers & Pages -> Create application -> Pages -> Connect to Git -> select `truthprojectofficial-max/pattern-auditor`
2. Build settings: leave all blank. The privacy policy is a single static HTML, no build step.
3. Add custom domain `privacy.bsafecode.dev` (or whatever you prefer). Cloudflare provisions HTTPS automatically.
4. The page will be live at the custom domain within ~60 seconds.

## Step 4: create the Play Console app

1. Go to https://play.google.com/console and sign in.
2. Pay the $25 USD developer registration fee. (One-off, good for life.)
3. Click "Create app". App name: `Pattern AI-uditor`. Default language: English. App or game: App. Free or paid: Free.
4. Click "Create".

## Step 5: dashboard tasks

The Play Console will show a checklist. Work through it in this order:

### 5.1 App access

"All functionality is available without special access." (Nothing requires login or special access.)

### 5.2 Ads

"Your app does not contain ads." (Select Yes.)

### 5.3 Content rating

Open the questionnaire. Use the answers in `console-answers/CONTENT_RATING.md`. Every question is "No".

### 5.4 Data safety

Open the form. First question: "Does your app collect or share any of the required user data types?" -> **No**. Form ends. (The full per-question answers are in `console-answers/DATA_SAFETY.md` in case the form asks follow-ups.)

### 5.5 Government app declarations

No.

### 5.6 Financial features

No.

### 5.7 Health apps

No.

### 5.8 Privacy policy

Paste the URL from Step 3.

## Step 6: store listing

On the left sidebar, click "Main store listing". Fill in:

- **App name**: `Pattern AI-uditor`
- **Short description**: copy from `store-listing/STORE_LISTING.md`
- **Full description**: copy from same file
- **App icon**: upload `option-X/icon-512.png` (where X is your palette pick)
- **Feature graphic**: upload `option-X/feature-1024x500.png`
- **Phone screenshots**: upload the four PNGs from `option-a/screenshots/` in order
- **Category**: Tools
- **Tags**: utility, text analysis, security, privacy
- **Contact**: truth.project.official@gmail.com
- **Support URL**: https://github.com/truthprojectofficial-max/pattern-auditor/issues
- **Marketing URL** (optional): https://github.com/truthprojectofficial-max/pattern-auditor
- **Privacy policy URL**: from Step 3
- **Pricing**: Free

## Step 7: release

On the left sidebar, click "Release" -> "Internal testing" (or "Closed testing" if you prefer a smaller list).

- Click "Create new release"
- Upload `dist/Pattern-AI-uditor-v0.1.0.aab`
- Release name: `v0.1.0`
- Release notes: copy from `dist/RELEASE_NOTES.md`
- Review and roll out

## Step 8: tester list

In the "Testers" tab, create an email list of 2-4 friends. They will get a link to opt in and download. You and your friends test for 2 weeks. Report any crashes or feedback. I will address in v0.1.1.

## Step 9: promote to production

After 2 weeks of clean testing, in the "Release" page, click "Promote release" -> "Production". Google will review (1-7 days, typically 3 for a small new app with no surprises). When approved, the listing goes live.

## What you do not need to do

- Sign with your real keystore. Already done.
- Generate a release APK. Already in `dist/`.
- Set up analytics or crash reporting. The app has none.
- Set up a customer support system. The support URL is the GitHub issues page.
- Translate the listing. English is the only language the app supports.

## What to do if Google rejects

The most common reasons for rejection of a small, single-developer app:

- Privacy policy URL returns 404 or is on a domain that does not match. Fix the URL, resubmit.
- Data-safety form contradicts the privacy policy. Make sure the privacy policy says "does not collect" and the data-safety form says "No" for everything.
- The app uses INTERNET but the data-safety form says it does not. The app does not have INTERNET. Verify in the manifest.

If you get a rejection email, paste it to me and I will debug.