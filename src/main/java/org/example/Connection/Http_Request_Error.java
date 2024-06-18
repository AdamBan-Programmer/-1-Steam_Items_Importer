package org.example.Connection;

public class Http_Request_Error {

    public Http_Request_Error()
    {

    }

    //error
    public int httpRequestError(int index) throws InterruptedException {
        System.out.println("Time out... 120s");
        Thread.sleep(120000);// 2 min
        return index - 1;
    }
}
