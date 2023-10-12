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

void sendData(FILE *fp, int mySocket, struct sockaddr_in ServerAdrr)
{
    // Initialize
    char buffer[1024];
    sendto(mySocket, buffer, sizeof(buffer), 0, (struct sockaddr *)&ServerAdrr, sizeof(ServerAdrr));

    // Send data until end of the file
    while (fgets(buffer, sizeof(buffer), fp) != NULL)
    {
        cout << "Myles & Trista -  Data being sent: " << buffer;

        sendto(mySocket, buffer, sizeof(buffer), 0, (struct sockaddr *)&ServerAdrr, sizeof(ServerAdrr));
        memset(buffer, '\0', sizeof(buffer));
    }

    strcpy(buffer, "END"); // When done, change buffer to end
    sendto(mySocket, buffer, sizeof(buffer), 0, (struct sockaddr *)&ServerAdrr, sizeof(ServerAdrr));

    fclose(fp);
}

int main()
{
    int mySocket, nBytes;
    char buffer[1024];
    struct sockaddr_in ServerAddr;
    int broadcastPermission = 1;

    mySocket = socket(PF_INET, SOCK_DGRAM, 0);
    setsockopt(mySocket, SOL_SOCKET, SO_BROADCAST, (void *)&broadcastPermission, sizeof(broadcastPermission));
    ServerAddr.sin_family = AF_INET;
    cout << "Myles & Trista -  Please enter the server port: ";
    cin.getline(buffer, 1023, '\n');
    ServerAddr.sin_port = htons(atoi(buffer));
    cout << "Myles & Trista -  Please enter the server IP: ";
    memset(buffer, '\0', sizeof(buffer));
    cin.getline(buffer, 1023, '\n');
    ServerAddr.sin_addr.s_addr = inet_addr(buffer);
    memset(ServerAddr.sin_zero, '\0', sizeof(ServerAddr.sin_zero));
    socklen_t addr_size = sizeof ServerAddr;
    do
    {
        FILE *filename;

        // Get input from user
        cout << "Myles & Trista -  Please enter the file name: ";
        memset(buffer, '\0', sizeof(buffer));
        cin.getline(buffer, 1023, '\n');

        filename = fopen(buffer, "r"); // Open the file to read

        if (filename == NULL) // If it doesn't exist
        {
            cout << "Myles & Trista -  Error opening file!" << endl; // Displays error message
            strcpy(buffer, "Quit!");                                 // Changes buffer to "Quit!"
        }
        else
        {
            strcpy(buffer, "Start!");                 // Tells server to start
            sendData(filename, mySocket, ServerAddr); // Sending the file data to the server
            cout << "\nMyles & Trista -  Successfully transferred the file." << endl;
            strcpy(buffer, "Quit!");
        }

        nBytes = strlen(buffer) + 1;
        sendto(mySocket, buffer, nBytes, 0, (struct sockaddr *)&ServerAddr, addr_size);
    } while (strcmp(buffer, "Quit!") != 0);
    close(mySocket);
    return 0;
}
