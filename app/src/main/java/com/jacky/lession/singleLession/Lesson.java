package com.jacky.lession.singleLession;

import com.jacky.lession.WeekDay;

import java.util.HashMap;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lesson {
    public static final Pattern timeRangePattern = Pattern
            .compile("^[\\s\\S]+?(\\d+)-(\\d+)&nbsp;星期(\\d+):(\\d+)-(\\d+)节&nbsp;([^<>]+)<br>");

    private String teacher;
    private String lessonName;
    private final String teachProject;

    private final HashMap<Integer, TimeRange> timeRanges = new HashMap<>();

    public Lesson(String teacher, String Name, String teachProject) {
        this.setTeacher(teacher);
        setLessonName(Name);
        this.teachProject = teachProject;
    }

    public String getLessonName() {
        return lessonName;
    }

    private void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public String getTeacher() {
        return teacher;
    }

    private void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getTeachProject() {
        return teachProject;
    }

    public void loadNewTimeRangeFormTeachingProject(String html) {
        int matchStatartIndex = 0;
        String usingString = html.substring(matchStatartIndex);

        Matcher result = timeRangePattern.matcher(usingString);
        while (result.lookingAt()) {
            int wStart = Integer.parseInt(Objects.requireNonNull(result.group(1)));
            int wEnd = Integer.parseInt(Objects.requireNonNull(result.group(2)));
            int week = Integer.parseInt(Objects.requireNonNull(result.group(3)));
            int start = Integer.parseInt(Objects.requireNonNull(result.group(4)));
            int end = Integer.parseInt(Objects.requireNonNull(result.group(5)));
            String room = result.group(6);
            timeRanges.put(week, new TimeRange(start, end, week, wStart, wEnd, room));

            matchStatartIndex = result.end();
            usingString = usingString.substring(matchStatartIndex);
            result = timeRangePattern.matcher(usingString);
        }

    }

    public boolean hasClass(int week, WeekDay weekDay, int classTime) {
        TimeRange t = timeRanges.get(weekDay.getWeekDay());
        if (t != null) {
            return t.lessonMatch(classTime, weekDay, week);
        }
        return false;
    }

    public TimeRange getClass(WeekDay weekDay) {
        return timeRanges.get(weekDay.getWeekDay());
    }

}
