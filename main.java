import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.Headers;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class Main {
    static KnowledgeBase base = new KnowledgeBaseImpl();

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/feed_knowledge", new FeedKnowledgeHandler());
        server.createContext("/answer_fib", new AnswerFibHandler());
        server.createContext("/print_all_knowledge", new PrintAllKnowledgeHandler());
        server.setExecutor(null);
        server.start();
    }

    static class FeedKnowledgeHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if ("POST".equals(exchange.getRequestMethod())) {
                InputStream is = exchange.getRequestBody();
                String jsonInput = new String(is.readAllBytes());
                base.feedKnowledge(jsonInput);
                String response = "Knowledge fed"; // Placeholder response
                exchange.sendResponseHeaders(200, response.length());
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            } else {
                exchange.sendResponseHeaders(405, -1); // Method Not Allowed
            }
        }
    }

    static class PrintAllKnowledgeHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if ("POST".equals(exchange.getRequestMethod())) {   
                InputStream is = exchange.getRequestBody();
                String jsonInput = new String(is.readAllBytes());
                base.printAllKnowledge();
                OutputStream os = exchange.getResponseBody();
                String response = "Knowledge printed on console";
                exchange.sendResponseHeaders(200, response.length());
                os.write(response.getBytes());
                os.close();
            } else {
                exchange.sendResponseHeaders(405, -1); // Method Not Allowed
            }
        }
    }

    static class AnswerFibHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if ("GET".equals(exchange.getRequestMethod())) {
                // Placeholder logic for Fibonacci answer
                String response = "Fibonacci answer"; // Placeholder response
                exchange.sendResponseHeaders(200, response.length());
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            } else {
                exchange.sendResponseHeaders(405, -1); // Method Not Allowed
            }
        }
    }
}
