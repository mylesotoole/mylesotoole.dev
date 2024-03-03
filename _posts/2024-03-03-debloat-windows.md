---
title: "Debloat Windows"
categories:
  - Windows
---

## Debloat Windows 11

### Powershell (Admin):

#### Remove all preinstalled Microsoft apps (can be redownloaded again through the store):

```powershell
Get-AppxPackage -AllUsers | Remove-AppxPackage
```

#### Add back Microsoft store:

```powershell
Get-AppxPackage -allusers Microsoft.WindowsStore | Foreach {Add-AppxPackage -DisableDevelopmentMode -Register "$($_.InstallLocation)\AppXManifest.xml"}
```

<hr>

## Maximize Storage in Windows 11

### Command Prompt (Admin):

#### Enable the built in CompactOS feature:

```cmd
Compact.exe /CompactOS:always
```