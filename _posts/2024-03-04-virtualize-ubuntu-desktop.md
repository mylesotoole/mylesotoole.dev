---
title: "Virtualize Ubuntu Desktop on Apple Silicon"
categories:
  - macOS
---

1. Download and install [**Ubuntu Server Arm**](https://ubuntu.com/download/server/arm) iso file.

2. Follow the installer and login to the Ubuntu Server virtual machine.

3. Remove unnecessary Ubuntu Server packages and download Ubuntu desktop:
   ```console
   sudo apt purge --autoremove ubuntu-server -y && sudo apt-get install --no-install-recommends ubuntu-desktop -y && sudo reboot
   ```

4. Once rebooted, fix dark mode:
   ```console
   sudo apt purge ubuntu-session yaru-theme-gnome-shell yaru-theme-gtk yaru-theme-icon yaru-theme-sound && sudo apt install ubuntu-session yaru-theme-gnome-shell yaru-theme-gtk yaru-theme-icon yaru-theme-sound
   ```

5. Enjoy a bloatware-free and lightweight Ubuntu ARM desktop
