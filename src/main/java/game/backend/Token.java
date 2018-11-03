package game.backend;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Token {
	public String getToken() {
		String token;
		try {
			/* Puts together the url */
			String api_url = "https://opentdb.com/api_token.php?command=request";
			URL url = new URL(api_url);

			/* Get the data from the url */
	        URLConnection connection = url.openConnection();
	        InputStream inputStream = connection.getInputStream();
	        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
	        token = bufferedReader.readLine();

		} catch (IOException e) {
			token = null;
		}
		
		if (token != null) {
			token = token.substring(79, token.length()-2);
		}
		
		return token;
	}
}
