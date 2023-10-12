# File Share

<link rel="stylesheet" type="text/css" href="assets/css/style.scss" />
<link rel="apple-touch-icon" sizes="180x180" href="/apple-touch-icon.png">
<link rel="icon" type="image/png" sizes="32x32" href="/favicon-32x32.png">
<link rel="icon" type="image/png" sizes="16x16" href="/favicon-16x16.png">
<link rel="manifest" href="/site.webmanifest">

[Back to Portfolio](/)

<h2 style="font-size: 30px">Network File Share</h2>

- **Groupmate:** Trista Kulesa
- **Class:** CSCI 332
- **Grade:** B
- **Language:** C++
- **Source Code Repository:**
  [ot8le/NetworkFileShare](https://github.com/ot8le/ot8le.github.io/tree/master/src/NetworkFileShare)  
  <!-- (Please [email me](mailto:mpotoole@csustudent.net?subject=GitHub%20Access) to request access.) -->

## Project Description

This program creates a UDP server side and client side terminal able to send or receive _.txt_ files
over the same network. By sending the file over from the server side, it sends and outputs
individual strings and stitches them together in a new document on the client side named the same
thing. It will not work if the document doesn't exist or is not a _.txt_ file.

## How to Compile and Run the Program

How to compile and run the project via Terminal with g++:

```bash
$ g++ server.cpp && ./Server.out
```

```bash
$ g++ client.cpp && ./Client.out
```

## UI Design

The user can set up a server with a listening port to receive a file. The client enters the server
port, server IP address, and the name of the file he/she wishes to send. If the file does not exist
an error message will be shown.<br />

<em>Fig 1. The server-side prompt.</em> ![screenshot](/images/nfs-figure1.jpg)<br />

<em>Fig 2. The client-side prompt.</em> ![screenshot](/images/nfs-figure2.jpg)<br />

<em>Fig 3. Feedback when the file does not exist.</em> ![screenshot](/images/nfs-figure3.jpg)<br />

## Additional Considerations

- The program outputs the part files as they are being sent or received to both terminals.

[Back to Portfolio](/)
