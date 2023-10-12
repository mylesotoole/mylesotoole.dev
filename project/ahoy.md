# Ahoy!

<link rel="stylesheet" type="text/css" href="assets/css/style.scss" />
<link rel="apple-touch-icon" sizes="180x180" href="/apple-touch-icon.png">
<link rel="icon" type="image/png" sizes="32x32" href="/favicon-32x32.png">
<link rel="icon" type="image/png" sizes="16x16" href="/favicon-16x16.png">
<link rel="manifest" href="/site.webmanifest">

[Back to Portfolio](/)

<h2 style="font-size: 30px">Ahoy!</h2>

- **Class:** CSCI 499
- **Grade:** A+
- **Language:** Swift
- **Source Code Repository:** [ot8le/Ahoy](https://github.com/ot8le/Ahoy) <br> (Please
  [email me](mailto:mpotoole@outlook.com?subject=GitHub%20Access) to request access.)

## Project Description

An interactive mobile application designed primarily for student communication at Charleston
Southern University. Runs on iOS version 13.0 and above with dimensions of all iPhones. Students
will be able to communicate with their classmates and resident assistants can communicate with their
residents. As the user you will be able to create and send chats when signing up with your student
or staff email. Everything is fetched and stored via a curated schema with Google Firebase Realtime
Database through Google Firebase Authentication.

## How to Compile and Run the Program

Install dependencies via terminal:

```bash
$ brew install cocoapods
$ pod install
```

- The app is fully built and deployed through Xcode 14.2 and above.
- Requires an internet connection.

## UI Design

![screenshot](/images/ahoy.gif)

## Features

- Creates an account via BucAddress email.
- Saves user data in Firebase.
- User login via email and password.
- Automatic login verification.
- Logout of the app.
- Search for users to start a new conversation.
- Pull name of conversed user for conversations and search.
- Add to chats or start a new one.
- Send messages and upload them to Firebase realtime database.
- Save conversations in Firebase with an generated message ID and conversation ID.
- Pull conversations from Firebase into the user's chat view interface.
- Saves date and time of messages in the cloud.
- Has a friendly minimal user interface.

[Back to Portfolio](/)
