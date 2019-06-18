package com.zcy.mytool;

import com.zcy.mytool.jokesoup.service.ContentService;

public class Test {

    public static void main(String[] args) throws Exception {
        ContentService contentService = ContentService.getInstance();
        System.out.println(contentService.getNextContent());
    }

}
