package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;

public class Crawler {
    HashSet<String> urlSet;
    int MAX_DEPTH;

    Crawler() {
        urlSet = new HashSet<>();
        MAX_DEPTH = 2;
    }

    public void getPageTextAndLinks(String url, int depth) {
        if (urlSet.contains(url) || depth > MAX_DEPTH) {
            return;
        }

        urlSet.add(url);
        depth = depth + 1;

        try {
            Document document = Jsoup.connect(url).timeout(5000).get();

            // Indexer work starts here
            System.out.println(document.title());
            Elements availableLinksOnPage = document.select("a[href]");

            for (Element currentLink : availableLinksOnPage) {
                String absoluteUrl = currentLink.absUrl("href"); // Convert to absolute URL
                if (!absoluteUrl.isEmpty() && !urlSet.contains(absoluteUrl)) {
                    getPageTextAndLinks(absoluteUrl, depth);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Crawler crawler = new Crawler();
        crawler.getPageTextAndLinks("https://www.javatpoint.com", 1);
    }
}
