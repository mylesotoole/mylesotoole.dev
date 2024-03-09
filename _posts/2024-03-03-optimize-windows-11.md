---
title: "Optimize Windows 11"
categories:
  - Windows
---

### Powershell (Admin):

#### Remove all preinstalled bloatware (these apps can be redownloaded again through the Microsoft Store):
```powershell
Get-AppxPackage -AllUsers | Remove-AppxPackage
```

#### To add back Microsoft Store:
```powershell
Get-AppxPackage -allusers Microsoft.WindowsStore | Foreach {Add-AppxPackage -DisableDevelopmentMode -Register "$($_.InstallLocation)\AppXManifest.xml"}
```

#### Enable the built in CompactOS feature, to maximize drive space:
```console
Compact.exe /CompactOS:always
```
