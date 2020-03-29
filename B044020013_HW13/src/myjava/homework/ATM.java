package myjava.homework;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public abstract class ATM {
    protected abstract void run();
    protected abstract void closeConnection();
    protected abstract void processConnection() throws IOException;
    protected abstract void sendData(String message) throws Exception;
    protected abstract String getStreams() throws Exception;
}
