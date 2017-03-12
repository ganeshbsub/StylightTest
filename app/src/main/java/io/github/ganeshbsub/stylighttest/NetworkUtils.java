package io.github.ganeshbsub.stylighttest;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by Zeo on 12-03-2017.
 */

public class NetworkUtils {

    final static String STYLIGHT_BASE_URL =
            "https://api.stylight.com/rest/brands";

    final static String PARAM_QUERY = "apiKey";
    final static String PARAM = "C6490912AB3211E680F576304DEC7EB7";


    public static URL buildUrl() {
        Uri builtUri = Uri.parse(STYLIGHT_BASE_URL).buildUpon()
                .appendQueryParameter(PARAM_QUERY, PARAM)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    /**
     * This method returns the entire result from the HTTP response.
     *
     * @param url The URL to fetch the HTTP response from.
     * @return The contents of the HTTP response.
     * @throws IOException Related to network and stream reading
     */
    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}
