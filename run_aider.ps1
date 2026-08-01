$ErrorActionPreference = 'Stop'
$env:OLLAMA_API_BASE = 'http://127.0.0.1:11434'
$aiderExe = Join-Path $PSScriptRoot '.venv311\Scripts\aider.exe'
if (-not (Test-Path -LiteralPath $aiderExe)) {
    throw "Aider is not installed at $aiderExe"
}
Set-Location -LiteralPath $PSScriptRoot
& $aiderExe --config (Join-Path $PSScriptRoot '.aider.conf.yml') --no-analytics --no-auto-commits @args
