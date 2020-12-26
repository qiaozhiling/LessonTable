package com.jacky.lession.singleLession;

import com.jacky.lession.WeekDay;

public class TimeRange {
    private final int start;
    private final int end;

    private final int weekDayIn;

    private final int weekStart;
    private final int weekEnd;

    private final String roomName;

    TimeRange(int start, int end, int weekDayIn, int weekStart, int weekEnd, String roomName) {
        this.start = start;
        this.end = end;
        this.weekDayIn = weekDayIn;
        this.weekStart = weekStart;
        this.weekEnd = weekEnd;
        this.roomName = roomName;
    }

    boolean lessonMatch(int lessonNumber, WeekDay weekDay, int week) {
        return hasClassInTargetLessonTime(lessonNumber) &&
                hasClassInTargetDay(weekDay) &&
                hasClassInTargetWeek(week);
    }

    boolean hasClassInTargetWeek(int week) {
        return week >= weekStart
                && week <= weekEnd;
    }

    boolean hasClassInTargetDay(WeekDay weekDay) {
        return weekDay.getWeekDay() == weekDayIn;
    }
    boolean hasClassInTargetLessonTime(int lessonNum){
        return lessonNum >= start && lessonNum <= end;
    }

    public String getLessonRoom() {
        return roomName;
    }

    int getWeekDayIn() {
        return weekDayIn;
    }

}
