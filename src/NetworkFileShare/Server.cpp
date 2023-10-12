/*
Student Name:  Myles O’Toole
Program Name:  Network File Share
Creation Date:  Fall 2021
Last Modified Date:  Fall 2022
CSCI Course:  CSCI 332
Grade Received:  B
Design Comments: This program creates a UDP server side and client side terminal able to send or receive .txt files over the same network. By sending the file over from the server side, it sends and outputs individual strings and stitches them together in a new document on the client side named the same thing. It will not work if the document doesn’t exist or is not a .txt file.
*/

#include <iostream>
#include <sys/socket.h>
#include <netinet/in.h>
#include <string.h>
#include <arpa/inet.h>
#include <unistd.h>

using namespace std;

void getFile(int udpSocket, struct sockaddr_in ClientAddr)
{
    char buffer[1024];
    socklen_t addr_size;
    char *filename = "received.txt";
    FILE *fp = fp = fopen(filename, "w"); // create new text file to write to

    // Receiving the data and writing it into the file.
    while (true)
    {
        addr_size = sizeof(ClientAddr);
        recvfrom(udpSocket, buffer, sizeof(buffer), 0, (struct sockaddr *)&ClientAddr, &addr_size);

        // Will recieve complete from client after end of file.
        if (strcmp(buffer, "END") == 0)
        {
            break; // Break out of while loop
        }

        cout << "Myles & Trista -  Data being received: " << buffer;
        fprintf(fp, "%s", buffer); // write to new file
        memset(buffer, '\0', sizeof(buffer));
    }
    fclose(fp); // Close file stream
}

int main()
{
    int udpSocket, nBytes;
    char buffer[1024];
    struct sockaddr_in ServerAddr, ClientAddr;
    char *ClientIP;

    udpSocket = socket(PF_INET, SOCK_DGRAM, 0);
    ServerAddr.sin_family = AF_INET;
    cout << "Myles & Trista -  Please add your listening port: ";
    cin >> buffer;
    ServerAddr.sin_port = htons(atoi(buffer));
    ServerAddr.sin_addr.s_addr = INADDR_ANY;
    memset(ServerAddr.sin_zero, '\0', sizeof(ServerAddr.sin_zero));
    bind(udpSocket, (struct sockaddr *)&ServerAddr, sizeof(ServerAddr));
    socklen_t addr_size = sizeof ClientAddr;

    do
    {
        memset(buffer, '\0', sizeof(buffer));
        nBytes = recvfrom(udpSocket, buffer, 1024, 0, (struct sockaddr *)&ClientAddr, &addr_size);
        buffer[nBytes] = '\0';
        ClientIP = inet_ntoa(ClientAddr.sin_addr);

        if (strcmp(buffer, "Quit!") != 0) // As long as buffer isnt "Quit!"...
        {
            cout << "Myles & Trista -  Received file to be transfered." << endl;
            getFile(udpSocket, ClientAddr);                                        // Gets and writes file from client
            cout << "\nMyles & Trista -  Successfully received the file." << endl; // Output success
            strcpy(buffer, "Quit!");                                               // Changes buffer to "Quit!", exits the loop
        }

    } while (strcmp(buffer, "Quit!") != 0);
    close(udpSocket);
    return 0;
}
