package web.login;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.StringTokenizer;

import control.DataBase;

public class ClientHandler implements Runnable {
	private final Socket socket;

	public ClientHandler(Socket socket) {
		this.socket = socket;
		
		
	}

	@Override
	public void run() {
		System.out.println("\nClientHandler Started for " + this.socket);
		while (true) {
			handleRequest(this.socket);
		}
	}

	public void handleRequest(Socket socket) {
		try {

			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String headerLine = in.readLine();
			if (headerLine != null) {

				System.out.println(headerLine);

				// A tokenizer is a process that splits text into a series of tokens
				StringTokenizer tokenizer = new StringTokenizer(headerLine);

				// The nextToken method will return the next available token
				String httpMethod = tokenizer.nextToken();

				// The next code sequence handles the GET method. A message is displayed on the
				// server side to indicate that a GET method is being processed
				if (httpMethod.equals("GET")) {
					System.out.println("Get method processed");
					String httpQueryString = tokenizer.nextToken();
					System.out.println(httpQueryString);

					if (httpQueryString.equals("/")) {
						StringBuilder responseBuffer = new StringBuilder();
						String str = "";
						BufferedReader buf = new BufferedReader(
								new FileReader(System.getProperty("user.dir") + "/src/web/login/templates/login.html"));

						while ((str = buf.readLine()) != null) {
							responseBuffer.append(str);

						}
						sendResponse(socket, 200, responseBuffer.toString());
						buf.close();
					}

					if (httpQueryString.contains("/?user=")) {

						System.out.println("Get method processed");
						String[] response = httpQueryString.split("user=");
						response = response[1].split("&password=");

						String email = response[0];
						String password = response[1];
						System.out.println(email + " " + password);

						StringBuilder responseBuffer = new StringBuilder();

						String str = "";
						BufferedReader buf = null;

						if (validarLogin(email, password)) {

								DataBase.loadScores();
								ArrayList<String> data = DataBase.map.get(email);
								
								responseBuffer =  new StringBuilder();
								responseBuffer
								.append("<html>")
								.append("<head>")
								.append("<style>")
									.append("body{")
										.append("background-image: url(\"https://backgrounddownload.com/wp-content/uploads/2018/09/agario-background-7.png\");")
										.append("background-size: cover;")
										.append("background-attachment: fixed;")
										.append("text-align: center;")
									.append("}")
									.append("table{")
										.append("margin : auto;")
										.append("border: 2px solid #000;")
										.append("border-collapse: collapse;")
										.append("width: 70%;")
									.append("}")
									.append("table th{")
										.append("padding: 4px;")
										.append("background-color: blue;")
										.append("text-align: center;")
										.append("font-size: 20px;")
									.append("}")
									.append("table td{")
										.append("border: 1px solid #000;")
										.append("padding: 13px;")
										.append("background-color: white;")
										.append("text-align: center;")
										.append("font-size: 13px;")
									.append("}")
									.append("table tr:hover{")
										.append("background: #666666;")
									.append("}")
									.append("table td:hover{")
									.append("background: green;")
									.append("color:white;")
								.append("}")
								.append("</style>")
								.append("<title>Table</title>")
								.append("</head>")
								.append("<body>")
								.append("<h1> Registro del jugador</h1>")
								.append("<table>")
								.append("<tr>")
								.append("<th><strong>Date</strong></th>")
								.append("<th><strong>comidos</strong></th>")
								.append("<th><strong>Score</strong></th>")
								.append("<th><strong>gano</strong></th>");
								
								for (int i = 0; i < data.size(); i++) {
									String[] list = data.get(i).split(";"); 
									responseBuffer.append("<tr>");
									responseBuffer.append("<td>"+list[0]+"</td>");
									responseBuffer.append("<td>"+list[1]+"</td>");
									responseBuffer.append("<td>"+list[2]+"</td>");
									responseBuffer.append("<td>"+list[3]+"</td>");
									responseBuffer.append("<tr>");
							}
								responseBuffer.append("<body>")
								.append("<table>")
								.append("<body>")
								.append("</html>");
								
								sendResponse(socket, 200, responseBuffer.toString());		

						}else {
							buf = new BufferedReader(new FileReader(
									System.getProperty("user.dir") + "/src/web/login/templates/invalidLogin.html"));
						}

						while ((str = buf.readLine()) != null) {
							responseBuffer.append(str);

						}

						sendResponse(socket, 200, responseBuffer.toString());

					}

				}

				else {
					System.out.println("The HTTP method is not recognized");
					sendResponse(socket, 405, "Method Not Allowed");
				}

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private boolean validarLogin(String email, String password) {
		LoginClient lc = new LoginClient(email, password);
		return lc.validateLogin();
	}

	public void sendResponse(Socket socket, int statusCode, String responseString) {
		String statusLine;
		String serverHeader = "Server: WebServer\r\n";
		String contentTypeHeader = "Content-Type: text/html\r\n";

		try {
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			if (statusCode == 200) {
				statusLine = "HTTP/1.0 200 OK" + "\r\n";
				String contentLengthHeader = "Content-Length: " + responseString.length() + "\r\n";
				out.writeBytes(statusLine);
				out.writeBytes(serverHeader);
				out.writeBytes(contentTypeHeader);
				out.writeBytes(contentLengthHeader);
				out.writeBytes("\r\n");
				out.writeBytes(responseString);
			} else if (statusCode == 405) {
				statusLine = "HTTP/1.0 405 Method Not Allowed" + "\r\n";
				out.writeBytes(statusLine);
				out.writeBytes("\r\n");
			} else {
				statusLine = "HTTP/1.0 404 Not Found" + "\r\n";
				out.writeBytes(statusLine);
				out.writeBytes("\r\n");
			}
			// out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
