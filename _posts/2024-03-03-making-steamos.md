---
title: "Turn Windows Into Steam Deck OS"
categories:
  - Windows
---

###### Notice: Outside of turning your PC into a game console, this is not the best security practice.

#### Needed Prerequisites:
- Install [Steam](https://store.steampowered.com/about/)
- Install [Discord](https://discord.com/) or chat app of choice
- Any user account with a password

#### Recommended Prerequisite:
- Follow [this quick guide](*) to save resources and storage

<hr>

### Change Default Login Shell

1. Open Registry Editor, navigate to:
    ``` 
    Computer\HKEY_LOCAL_MACHINE\SOFTWARE\Microsoft\Windows NT\CurrentVersion\Winlogon
    ```

2. Double Click on ***Shell*** and replace ***Value data*** with:
    ```
    "C:\Program Files (x86)\Steam\steam.exe"
    ```
###### To log back into regular windows, change this to `explorer.exe`

### Enable Passwordless Login

1. Open Registry Editor, navigate to:
    ```
    HKLM\SOFTWARE\Microsoft\Windows NT\CurrentVersion\PasswordLess\Device
    ```

2. Double Click on ***DevicePasswordLessBuildVersion*** and replace ***Value data*** with:
    ```
    0
    ```

3. Go to **Accounts** **> Sign-in** options and scroll down to **Additional settings**

   - Set **If you've been away, when should Windows require you to sign in again?** to `Never`

   - Set **Use my sign-in info to automatically finish setting up after an update** to `On`

4. Search for and open **netplwiz**. 
   - `Uncheck` **Users must enter a user name and password to use this computer.**

### Enable Discord
###### You can use this method for any program you want to start at login. Regular `Startup Apps` will not work as they only launch in the default shell. 
###### Add a new basic task for each app you want to open at login. Reuse the steps, but for `Step 8` change the path to the app you want to open.

1. Pin **Discord** to your taskbar, **right click** Discord, **right click** Discord, and select `Properties`.
2. Hover over the blue highlighted text, the file target, and copy it to your clipboard.
3. Search for **Task Scheduler**
4. Click **Action** and then click **Create Basic Task**
5. Name it `Discord` and press **Next**
6. For **When do you want the task to start?** select `When I log in`
7. For **What action do you want the task to perform?** select `Start a program`

8. For **Program/script** paste Discord's **target** from your clipboard. It should look like this (where `name` is your account name):
    ```
    C:\Users\name\AppData\Local\Discord\Update.exe --processStart Discord.exe
    ```
9. Press `next` and then `yes` to the prompt.

10. `Check` **Open the Properties dialog for this task when I click Finish** and then press **Finish**.
11. Go to the `Conditions` tab and `Uncheck` **Start the task if the computer is on AC power**.
12. Go to the `Settings` tab and `Uncheck` **Stop if the task does not end when requested, force it to stop**.
13. Press `OK`.

### Configure Steam

1. Open Steam, and click `Steam`, then click `Settings` then open the `Interface` tab.
   - Set **Run Steam when my computer starts** to `On`
   - Set **Start Steam in Big Picture Mode** to `On`