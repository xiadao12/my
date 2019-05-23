package com.zcy.jokesoup.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.HashSet;
import java.util.Set;

public class ContentService {
    private static final ContentService ourInstance = new ContentService();

    public static ContentService getInstance() {
        return ourInstance;
    }

    private Set<String> contentSet = new HashSet<>();

    private ContentService() {
    }

    /**
     * 获取内容
     *
     * @return 内容
     */
    public void getContentByNetwork() throws Exception {

        System.out.println("getContentByNetwork");

        String urlString = "http://www.nows.fun/";
        Document nowsDocument = Jsoup.connect(urlString).get();

        Element sentenceElement = nowsDocument.getElementById("sentence");
        if (sentenceElement == null) {
            return;
        }

        String content = sentenceElement.text().trim();

        contentSet.add(content);
    }

    public String getNextContent() {

        System.out.println(contentSet.size());

        if (contentSet.size() < 5) {
            //for (int i = 0; i < 5; i++) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            getContentByNetwork();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
           // }
        }

        if (contentSet.size() <= 0) {
            return "无内容";
        }

        String content = contentSet.iterator().next();
        contentSet.remove(content);

        return content;
    }

}
