package org.usfirst.frc.team548.robot;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alex
 */
public class SoundServer {
    private static int port = 5801; //Port server is on. Should be 1180 for comp.
    private static SoundServer instance; //Instance of server
    private static ServerSocket server; //Socket that listens for incoming connections
    private static int delay = 100;
    private static ConnectionListener clientListener;
    private static volatile boolean boolData1 = false;

    /**
     * Creates new instance of server if their is none
     *
     * @return instance of server
     */
    public static SoundServer getInstance() {
        if (instance == null) {
            instance = new SoundServer();
        }
        return instance;
    }

    private SoundServer() {
        clientListener = new ConnectionListener();
    }

    /**
     * Starts server for listening for incoming client connections
     *
     * @param port What port the server is ran on. Use 1180 for competitions
     * @param timeout Timeout for client heartbeat in milliseconds. Don't use
     * number less then 1000
     */
    public static void startServer(int port, int timeout) {
        SoundServer.port = port;
        clientListener.startListener();
    }

    /**
     * Starts server for listening for incoming clients connections Uses default
     * port of 548. Use 1180 in competitions Uses default timeout of 2000ms
     */
    public static void startServer() {
        clientListener.startListener();
    }

    /**
     * Stops server and disconnects all clients
     */
    public static void stopServer() {
        try {
            clientListener.stopListener();
            server.close();
            
        } catch (IOException ex) {
            System.err.println("Error closing server: \"" + ex.getMessage() + "\"");
        }
        System.out.println("SERVER SHUT DOWN");
    }

    private class ConnectionListener implements Runnable {
        
        private Thread mainThread;
        private boolean listening; //true if listening for clients
        
        private ConnectionListener() {
            mainThread = new Thread(this);
        }
        
        @Override
        public void run() {
            try {
                server = new ServerSocket(port);
                System.out.println("RRCP Server Started!!!");
                while (listening) {
                    Socket s = server.accept();
                    System.out.println("Client Connected");
                    ConnectionHandler ch = new ConnectionHandler(s);
                    new Thread(ch).start();
                }
            } catch (IOException ex) {
                System.err.println("Error making client socket: \"" + ex.getMessage() + "\"");
            }
        }
        
        public void startListener() {
            listening = true;
            mainThread.start();
        }
        
        public void stopListener() {
           listening = false; 
        }
    }

    private class ConnectionHandler implements Runnable {

        private Socket s; //Socket server uses to comunicate with client
        //private DataInputStream dis;
        private DataOutputStream dos;

        public ConnectionHandler(Socket s) {
            this.s = s;
            try {
                //dis = new DataInputStream(new BufferedInputStream(s.getInputStream()));
                dos = new DataOutputStream(new BufferedOutputStream(this.s.getOutputStream()));
            } catch (IOException ex) {
                System.err.println("Error making data streams on ConnectionHandler: \"" + ex.getMessage() + "\"");
            }
        }

        @Override
        public void run() {
            protocol();
            this.close();
        }

        private void close() {
            try {
                //dis.close();
                s.close();
            } catch (IOException ex) {
                System.err.println("Error closing ConnectionHandler: \"" + ex.getMessage() + "\"");
            }
        }

        private void protocol() {
            try {
                while (true && SoundServer.clientListener.listening) {
                	if(boolData1) dos.write(1);
                	else dos.write(0);
                    dos.flush();
                    try {
                        Thread.sleep(delay);
                    } catch (InterruptedException ex) {
                        System.err.println("Error sleeping: \"" + ex.getMessage() + "\"");
                    }
                }         

            } catch (IOException ex) {
                
                System.err.println("Error reading data from client: \"" + ex.getMessage() + "\"");
            }
        }

    }
    
    public static void setBoolData1(boolean b) {
    	boolData1 = b;
    }
}
