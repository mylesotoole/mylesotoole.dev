---
title: "Optimize Windows 11"
categories:
  - Windows
---

## Remove bloatware:

### Powershell (Admin):

#### Remove all preinstalled Microsoft apps (they can be redownloaded again through the store):
```powershell
Get-AppxPackage -AllUsers | Remove-AppxPackage
```

#### Optionally, add back Microsoft store:
```powershell
Get-AppxPackage -allusers Microsoft.WindowsStore | Foreach {Add-AppxPackage -DisableDevelopmentMode -Register "$($_.InstallLocation)\AppXManifest.xml"}
```

<hr>

## Compress OS files to free up drive space:

### Command Prompt (Admin):

#### Enable the built in CompactOS feature:
```console
Compact.exe /CompactOS:always
```