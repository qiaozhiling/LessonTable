package com.jacky.lession.singleLession;

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

    boolean lessonMatch(int lessonNumber, int weekDay, int week) {
        return lessonNumber >= start && lessonNumber <= end && weekDay == weekDayIn && week <= weekStart
                && week >= weekEnd;
    }

    public String getLessonRoom() {
        return roomName;
    }

}
