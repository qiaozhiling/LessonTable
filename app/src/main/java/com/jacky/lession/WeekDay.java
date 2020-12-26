package com.jacky.lession;

public enum WeekDay {
    Monday(1),Tuesday(2),Wednesday(3),Thursday(4),Friday(5),Saturday(6),Sunday(7);

    private final int w;

    WeekDay(int w){

        this.w = w;
    }

    public int getWeekDay() {
        return w;
    }
}
