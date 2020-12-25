package com.jacky.lession;

import com.jacky.lession.err.NoClassException;

public class LessonDetail {
    private final String teacher;
    private final String name;
    private final String room;
    private boolean hasClass;

    private LessonDetail(String teacher, String name, String room, boolean hasClass) {
        this.teacher = teacher;
        this.name = name;
        this.room = room;
        this.setHasClass(hasClass);
    }

    public boolean HasClass() {
        return hasClass;
    }

    private void setHasClass(boolean hasClass) {
        this.hasClass = hasClass;
    }

    static LessonDetail NoClass() {
        return new LessonDetail("", "", "", false);
    }

    static LessonDetail NewLessonDetail(String teacher, String name, String room) {
        return new LessonDetail(teacher, name, room, true);
    }

    public String getTeacher() {
        if (!hasClass)
            throw new NoClassException();
        return teacher;
    }

    public String getLessonName() {
        if (!hasClass)
            throw new NoClassException();
        return name;
    }

    public String getRoom() {
        if (!hasClass)
            throw new NoClassException();
        return room;
    }
}
