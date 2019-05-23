package com.zcy.mytool;

import com.zcy.mytool.service.jokesoup.ContentService;

public class Test {

    public static void main(String[] args) throws Exception{
        ContentService contentService = ContentService.getInstance();
        System.out.println(contentService.getNextContent());;
    }

}
