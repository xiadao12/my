package com.zcy.mytool.service.jokesoup;

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

    // 用于存放内容
    private Set<String> contentSet = new HashSet<>();

    // 每次网上拉取次数
    private final static int PER_GETNETWORK_CONTENT = 5;

    // 剩余内容数
    private final static int REMAIN_SIZE = 5;

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

        if (contentSet.size() <= REMAIN_SIZE) {
            for (int i = 0; i < PER_GETNETWORK_CONTENT; i++) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            getContentByNetwork();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        }

        if (contentSet.size() <= 0) {
            return "检查网络点击重试";
        }

        String content = contentSet.iterator().next();
        contentSet.remove(content);

        return content;
    }

}
