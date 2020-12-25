package com.jacky.lession;

import java.io.IOException;
import java.net.MalformedURLException;

import com.jacky.lession.url.URLRequest;

public class LessonTable {

    private final String userID;
    private final String password;

    private final URLRequest session;

    public LessonTable(String userID, String password) {
        this.userID = userID;
        this.password = password;
        session=new URLRequest();

    }

    public LessionDetail getLessonTable() throws MalformedURLException, IOException {
        session.sendLogin(userID,password);
        return new LessionDetail();
    }

    public void getTargetClass(int classCount, int week) {
        if(session==null)
        {
            //todo： 抛出异常，先请求
        }
        return ;
    }
}
