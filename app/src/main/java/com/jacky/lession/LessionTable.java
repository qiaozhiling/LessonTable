package com.jacky.lession;

import java.io.IOException;
import java.net.MalformedURLException;

public class LessionTable {

    private String userID;
    private String password;

    private LessionInfomation infomation;

    public LessionTable(String userID, String password) {
        this.userID = userID;
        this.password = password;
    }

    public void getLessionTable() throws MalformedURLException, IOException {
        infomation=new LessionInfomation(userID,password);
    }

    public LessionDetail getTargetClass(int classCount, int week) {
        if(infomation !=null)
        {
            //todo： 抛出异常，先请求
        }
        return new LessionDetail();
    }
}
