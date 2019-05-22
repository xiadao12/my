package com.zcy.jokesoup.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class ContentService {
    private static final ContentService ourInstance = new ContentService();

    public static ContentService getInstance() {
        return ourInstance;
    }

    private ContentService() {
    }

    /**
     * 获取内容
     *
     * @return 内容
     */
    public String getContentByNetwork() throws Exception {

        String urlString = "http://www.nows.fun/";
        Document nowsDocument = Jsoup.connect(urlString).get();

        Element sentenceElement = nowsDocument.getElementById("sentence");
        if(sentenceElement == null){
            return "";
        }

        return sentenceElement.text().trim();
    }

}
