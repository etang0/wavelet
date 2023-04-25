import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

class Handler implements URLHandler {
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.
    int num = 0;
    String theStrings = "";

    public String handleRequest(URI url) {
        if (url.getPath().contains("/add-message")) {
            String[] newRequest = url.getQuery().split("=");
            if (theStrings == "") theStrings = newRequest[1];
            else theStrings += "\n" + newRequest[1];
        }
        else if (url.getPath().contains("/reset")) theStrings = "";
        return theStrings;
    }
}

class StringServer {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }
        int port = Integer.parseInt(args[0]);
        Server.start(port, new Handler());
    }
}
