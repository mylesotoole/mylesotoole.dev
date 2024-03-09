---
title: "Debloat Windows with Admin Powershell"
categories:
  - Windows
---

#### Remove all preinstalled bloatware (these apps can be redownloaded again through the Microsoft Store):
```powershell
Get-AppxPackage -AllUsers | Remove-AppxPackage
```

#### Optionally, adding back Microsoft Store:
```powershell
Get-AppxPackage -allusers Microsoft.WindowsStore | Foreach {Add-AppxPackage -DisableDevelopmentMode -Register "$($_.InstallLocation)\AppXManifest.xml"}
```

#### Enable the built in CompactOS feature, to reclaim storage space:
```powershell
Compact.exe /CompactOS:always
```
