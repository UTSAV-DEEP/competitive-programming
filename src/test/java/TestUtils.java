import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

public class TestUtils {
    private static final String CF_PAGE_SOURCE_URL = "https://codeforces.com/contest/%d/problem/%s";

    public static String getHTML(String urlToRead) throws Exception {
        StringBuilder result = new StringBuilder();
        URL url = new URL(urlToRead);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        try (BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line).append("\n");
            }
        }
        return result.toString();
    }

    public static List<String> getCFSampleTestCases(int contestId, String problem) throws Exception {

        String targetUrl=String.format(CF_PAGE_SOURCE_URL, contestId, problem);
        String html = getHTML(targetUrl);
        Document document = Jsoup.parse(html);
        List<String> sampleTests = new ArrayList<>();
        Elements paragraphs = document.select("div.sample-test").get(0).getElementsByTag("pre");
        for (Element paragraph : paragraphs) {
            sampleTests.add(paragraph.wholeText());
        }
        System.out.println(Arrays.toString(sampleTests.toArray()));
        return sampleTests;
    }
}
