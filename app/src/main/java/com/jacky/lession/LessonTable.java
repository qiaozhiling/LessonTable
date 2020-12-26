package com.jacky.lession;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

import com.jacky.lession.err.BadLoginStatuteException;
import com.jacky.lession.singleLession.Lesson;
import com.jacky.lession.singleLession.TimeRange;
import com.jacky.lession.url.URLRequest;

public class LessonTable {

    private final String userID;
    private final String password;

    private final URLRequest session;
    ArrayList<Lesson> lessons = null;

    // private URLRequest infomation;

    public LessonTable(String userID, String password) {
        this.userID = userID;
        this.password = password;
        session = new URLRequest();
    }

    public void getLessonTable() throws IOException {
        session.sendLogin(userID, password);
        lessons = session.loadTable();
        for (Lesson lesson : lessons) {
            lesson.loadNewTimeRangeFormTeachingProject(session.getTeachingProject(lesson.getTeachProject()));
        }
    }

    public void getTargetTable(int week) {
    }

    public LessonDetail getLesson(int week, int time, WeekDay weekDay) {
        if (lessons == null) {
            throw new BadLoginStatuteException("未登录！");
        } else {
            for (Lesson lesson : lessons) {
                if (lesson.hasClass(week, weekDay, time)) {
                    TimeRange t = lesson.getClass(weekDay);
                    return LessonDetail.NewLessonDetail(lesson.getTeacher(), lesson.getLessonName(),
                            t.getLessonRoom());

                }
            }
            return LessonDetail.NoClass();
        }
    }


}
