package com.jacky.lession;

import com.jacky.lession.singleLession.Lesson;
import com.jacky.lession.singleLession.TimeRange;

public class DailyLessonDetail {
    private final LessonDetail[] details;
    private final WeekDay weekDay;
    private final int week;


    public DailyLessonDetail(Lesson[] details,WeekDay weekDay,int week) {
        this.weekDay = weekDay;
        this.week = week;
        this.details = new LessonDetail[11];

        for (int i = 0; i < this.details.length; i++) {
            this.details[i]=getClass(details,i+1);
        }
    }

    private boolean findClass(Lesson[] lessons,int lessonNum){
        for (Lesson l :
                lessons) {
            if(l.hasClass(week,weekDay,lessonNum)){
                return true;
            }
        }return false;
    }
    private LessonDetail getClass(Lesson[] lessons,int lessonNum){
        for (Lesson l :
                lessons) {
            if(l.hasClass(week,weekDay,lessonNum)){

                return LessonDetail.NewLessonDetail(l.getTeacher(),l.getLessonName(),l.getClass(weekDay).getLessonRoom());
            }
        }return LessonDetail.NoClass();
    }

    public LessonDetail getLesson(int lesson){
        return details[lesson-1];
    }
}
