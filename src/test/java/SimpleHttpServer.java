import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleHttpServer {
    public static void main(String[] args) {

        // Use of Server Socket

        // Define port number
        final int port = 8080;
        // Bind the ServerSocket to the Specified Port Number
        try(ServerSocket serverSocket = new ServerSocket(port)){
            System.out.println("Running server at port "+port);

            // keep the server running and handling incoming requests
            while (true) {
                // Accept a client connection and create a Socket object for communication
                Socket socket = serverSocket.accept();
                handleRequest(socket);
            }
        } catch (IOException e){
            System.out.println("Error handling Request "+e.getMessage());
        }
    }

    // Method to handle client requests
    // Socket Class
    private  static void handleRequest(Socket socket){
        // Use try-with-resources to automatically close streams
        try(InputStream inputStream = socket.getInputStream();
        OutputStream outputStream = socket.getOutputStream()){
            // Read the HTTP request from the client
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = bufferedReader.readLine();
            String[] parts = line.split(" ");

            //The first part is GET method and second part is the URL path. Consider it invalid if there are less than two parts
            String requestMethod = parts[0];
            String path = parts[1];

            if("GET".equalsIgnoreCase(requestMethod) && "/messages".equalsIgnoreCase(path)){
                // Respond to the client with a predefined message
                writeResponse(outputStream);
            }
        } catch (IOException e){
            System.out.println("Failed to handel " + e.getMessage());
        } finally {
            // Ensure the socket is closed to release resources
            try {
                socket.close();
            } catch (IOException e){
                System.out.println("Error closing Socket: "+e.getMessage());
            }
        }
    }

    // Send Response and Test URL back to the client
    private static void writeResponse(OutputStream outputStream) throws IOException {
        // The message to be sent in the HTTP response body
        String message = "Hello From Deba951";
        // Construct the HTTP response headers and body
        // Don't forget the double new line char below. It's important to separate the header from body
        String httpResponse = """
                HTTP/1.1 200 OK
                Content-Type: text/plain
                Content-Length: """ + message.length() + "\n\n" +
                message;

        // Write the response to the output stream
        outputStream.write(httpResponse.getBytes());
        outputStream.flush();
        outputStream.close();
    }
}