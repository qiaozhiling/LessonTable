package com.jacky.lession.singleLession;

import java.io.BufferedReader;
import java.util.HashMap;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Lesson {
    public static final Pattern timeRangePattern=Pattern.compile("[.\\n]*(\\d+)-(\\d+)&nbsp;星期(\\d+):(\\d+)-(\\d+)节&nbsp;([^<>]+)<br>[.\\n]*");


    private String teacher;
    private String lessonName;
    private final HashMap<Integer,TimeRange>timeRanges=new HashMap<>();

    void loadNewTimeRangeFormTeachingProject(BufferedReader teachingProjectDataReader){
        //while (teachingProjectDataReader)
    }


}
