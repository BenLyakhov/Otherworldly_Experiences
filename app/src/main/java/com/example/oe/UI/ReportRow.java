package com.example.oe.UI;

import com.example.oe.entities.Excursion;
import com.example.oe.entities.Vacation;

public class ReportRow {
    private final Vacation vacation;
//    private final Excursion excursion;
    private final int excursionCount;
    public ReportRow(Vacation vacation, int excursionCount){
        this.vacation = vacation;
//        this.excursion = excursion; // removed this to make just excursioncount, for report
        this.excursionCount = excursionCount;
    }

    public Vacation getVacation() { return vacation; }
//    public Excursion getExcursion() { return excursion; }
    public int getExcursionCount() { return excursionCount; }
}
