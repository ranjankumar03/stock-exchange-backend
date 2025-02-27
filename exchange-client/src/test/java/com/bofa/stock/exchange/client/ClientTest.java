package com.stock.exchange.client;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Semaphore;

/**
 * @author ranjankumar on 30/1/2022
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(Client.class)
public class ClientTest {

    private static final int PORT = 8020;

    private OutputStream serverOut;
    private InputStream serverIn;

    /**
     * Shared lock between the "client" and "server" code, to make the test case
     * synchronous.
     */
    private Semaphore lock = new Semaphore(0);

    @Test
    public void testMainMethodVerification() {

        //given
        PowerMockito.mockStatic(Client.class);

        //when
        Client.main(Mockito.any());

        //then
        PowerMockito.verifyStatic(Client.class);
        Client.main(Mockito.any());
    }

    @Test
    public void testClientServerSocketCommunication() throws IOException, InterruptedException {

        ServerSocket serverSocket = new ServerSocket(PORT);
        listen(serverSocket);

        Socket client = new Socket("localhost", PORT);
        OutputStream clientOut = client.getOutputStream();
        InputStream clientIn = client.getInputStream();

        lock.acquire();

        write(clientOut, "Hi");
        assertRead(serverIn, "Hi");

        write(serverOut, "Hello");
        assertRead(clientIn, "Hello");

        printWrite(clientOut, "Test printWrite");
        assertRead(serverIn, "Test printWrite");

        printWrite(serverOut, "Test printWrite again");
        assertRead(clientIn, "Test printWrite again");

        client.close();
        serverSocket.close();
    }

    /**
     * Writes to an OutputStream. Used for both server and client output streams.
     */
    private void write(OutputStream out, String str) throws IOException {

        out.write(str.getBytes());
        out.flush();
    }

    /**
     * Writes to an OutputStream. Used for both server and client output streams.
     */
    private void printWrite(OutputStream out, String str) throws IOException {

        PrintWriter pw = new PrintWriter(out);
        pw.print(str);
        pw.flush();
    }

    /**
     * Reads from an InputStream. Used for both server and client input streams.
     */
    private void assertRead(InputStream in, String expected) throws IOException {

        Assert.assertEquals("Too few bytes available for reading: ", expected.length(), in.available());

        byte[] buf = new byte[expected.length()];
        in.read(buf);
        Assert.assertEquals(expected, new String(buf));
    }

    /**
     * Listens for request server side on a separate
     * thread and "signals" to the client side above through the shared lock object.
     */
    private void listen(ServerSocket server) {

        new Thread(() -> {
            try {
                Socket socket = server.accept();
                serverOut = socket.getOutputStream();
                serverIn = socket.getInputStream();
                lock.release();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
