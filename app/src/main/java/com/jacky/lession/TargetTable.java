package com.jacky.lession;

import com.jacky.lession.singleLession.Lesson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class TargetTable {
    private final int week;
    private final ArrayList<Lesson> lessons= new ArrayList<>();

    TargetTable(int week, ArrayList<Lesson> lessons) {
        this.week = week;

        for (Lesson l :
                lessons) {
            if (l.hasClassInTargetWeek(week)) {
                this.lessons.add(l.generateTargetWeekLesson(week));
            }
        }

    }

    public int getWeek() {
        return week;
    }

    public DailyLessonDetail getTargetDayTable(WeekDay weekDay) {
        LinkedList<Lesson> t = new LinkedList<>();
        for (Lesson l :
                lessons) {
            if (l.hasClassInTargetDay(weekDay)) {
                t.add(l);
            }
        }
        return new DailyLessonDetail((Lesson[]) t.toArray(), weekDay, week);
    }

}
