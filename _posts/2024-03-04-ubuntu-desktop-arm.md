---
title: "Create an ARM Ubuntu Desktop VM on Apple Silicon:"
categories:
  - Linux
  - macOS
---

## Minimal Ubuntu ARM Desktop for Apple Silicon:
1. Download and install [**Ubuntu Server Arm**](https://ubuntu.com/download/server/arm) iso file.

2. Start the Ubuntu Server virtual machine and login.

3. Remove unnecessary Ubuntu Server packages and download Ubuntu desktop:
   ```console
   sudo apt purge --autoremove ubuntu-server -y && sudo apt-get install --no-install-recommends ubuntu-desktop -y && sudo reboot
   ```

3. Once rebooted, fix dark mode:
   ```console
   sudo apt purge ubuntu-session yaru-theme-gnome-shell yaru-theme-gtk yaru-theme-icon yaru-theme-sound && sudo apt install ubuntu-session yaru-theme-gnome-shell yaru-theme-gtk yaru-theme-icon yaru-theme-sound
   ```

4. Enjoy a bloatware-free and lightweight Ubuntu ARM desktop