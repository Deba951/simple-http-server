# Simple HTTP Server in Java

This project demonstrates how to create a simple HTTP server in Java without using any external frameworks. The server listens on port 8080 and responds to HTTP `GET` requests to the `/messages` endpoint.

## Table of Contents
- [Features](#features)
- [Concepts Used](#concepts-used)
- [Code Overview](#code-overview)
- [How to Run](#how-to-run)
- [Endpoints](#endpoints)
- [Example Request and Response](#example-request-and-response)
- [Future Improvements](#future-improvements)

---

## Features
- Serves HTTP `GET` requests.
- Listens on port 8080.
- Responds with a simple message for the `/messages` endpoint.
- Demonstrates basic networking and socket programming in Java.

---

## Concepts Used

### 1. **ServerSocket**
- A `ServerSocket` is used to create a socket bound to a specific port number.
- It listens for incoming client connections.
- In this project, the server listens on port 8080.

### 2. **Socket**
- A `Socket` is used to handle communication between the server and the client.
- Each incoming client request is represented by a `Socket` object.

### 3. **InputStream and OutputStream**
- These are used to read data from and write data to the client.
- `InputStream`: Reads incoming client data (like HTTP request headers).
- `OutputStream`: Sends response data back to the client.

### 4. **BufferedReader**
- Reads data from the `InputStream` line-by-line for easier parsing.

### 5. **HTTP Protocol**
- The project implements basic HTTP handling:
  - Recognizes HTTP `GET` requests.
  - Returns an HTTP response with a `200 OK` status and a plain-text message.

---

## Code Overview

### Main Components
1. **`main()` Method**
   - Sets up the `ServerSocket` to listen on port 8080.
   - Accepts incoming client connections in a loop.
   - Delegates request handling to the `handleRequest` method.

2. **`handleRequest()` Method**
   - Parses the HTTP request.
   - Checks if the request is a `GET` request and matches the `/messages` endpoint.
   - Sends a response using the `writeResponse()` method.

3. **`writeResponse()` Method**
   - Constructs and sends an HTTP response.
   - Includes the `Content-Type` and `Content-Length` headers for proper HTTP formatting.

---

## How to Run

1. **Prerequisites**:
   - Ensure you have Java installed (JDK 8 or higher).

2. **Compile the Code**:
   ```bash
   javac SimpleHttpServer.java
   ```

3. **Run the Server**:
   ```bash
   java SimpleHttpServer
   ```

4. **Access the Server**:
   - Open your browser or a tool like Postman.
   - Navigate to: `http://localhost:8080/messages`.

---

## Endpoints

### `/messages`
- **Method**: `GET`
- **Response**:
  - Status: `200 OK`
  - Content: `Hello From Deba951`

---

## Example Request and Response

### Request:
```http
GET /messages HTTP/1.1
Host: localhost:8080
```

### Response:
```http
HTTP/1.1 200 OK
Content-Type: text/plain
Content-Length: 17

Hello From Deba951
```

---
