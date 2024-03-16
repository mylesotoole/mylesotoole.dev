---
title: "Disable Animations on Amazon Fire TV"
categories:
  - macOS
---

#### Needed Prerequisites:
- Install [Homebrew](https://brew.sh/) on your Mac

#### 1. Turn on Developer Mode:
1. Go to **Settings**
2. Click **Device & Software**
3. Click **About**
4. Click **Your TV** until developer mode is enabled

#### 2. Turn on ADB Debugging:
1. Go to **Settings**
2. Click **Device & Software**
3. Click **Developer options**
4. Turn **ADB debugging** on

3. Install Android Platform Tools:
   ```console 
   brew install android-platform-tools
   ```

4. Connect to Amazon Fire TV:
   ```console
   adb connect (Your Fire TV IP Address)
   ```

5. Disable Animations:
   ```console
   adb shell settings put global window_animation_scale 0.0
   ```

   ```console
   adb shell settings put global transition_animation_scale 0.0
   ```
