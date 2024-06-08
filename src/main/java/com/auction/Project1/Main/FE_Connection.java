package com.auction.Project1.Main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class FE_Connection {

	private String baseUrl;

	public FE_Connection(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	// Method to send a POST request
	private String sendPost(String endpoint, String jsonInputString) throws Exception {
		URL url = new URL(baseUrl + endpoint);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("POST");
		connection.setRequestProperty("Content-Type", "application/json; utf-8");
		connection.setRequestProperty("Accept", "application/json");
		connection.setDoOutput(true);

		try (OutputStream os = connection.getOutputStream()) {
			byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
			os.write(input, 0, input.length);
		}

		int responseCode = connection.getResponseCode();
		if (responseCode == HttpURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_CREATED) {
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String inputLine;
			StringBuilder response = new StringBuilder();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			return response.toString();
		} else {
			throw new Exception("POST request failed with response code: " + responseCode);
		}
	}

	// Method for user sign up
	public String signUp(String username, String password, String userID) throws Exception {
		String jsonInputString = String.format("{\"username\": \"%s\", \"password\": \"%s\", \"userID\": \"%s\"}", username, password, userID);
		return sendPost("signup", jsonInputString);
	}

	// Method for user sign in
	public String signIn(String username, String password) throws Exception {
		String jsonInputString = String.format("{\"username\": \"%s\", \"password\": \"%s\"}", username, password);
		return sendPost("/Login.app", jsonInputString);
	}
}