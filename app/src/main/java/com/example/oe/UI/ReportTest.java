package com.example.oe.UI;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.oe.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class ReportTest {

    // Launches the Report activity directly
    @Rule
    public ActivityScenarioRule<Report> activityRule =
            new ActivityScenarioRule<>(Report.class);

    // Test 1: RecyclerView should be visible on screen load
    @Test
    public void recyclerView_isDisplayedOnLoad() {
        onView(withId(R.id.report_recycler_view))
                .check(matches(isDisplayed()));
    }

    // Test 2: RecyclerView should have at least 1 row on load (vacations exist in DB)
    @Test
    public void recyclerView_hasRowsOnLoad() {
        onView(withId(R.id.report_recycler_view))
                .check(matches(hasMinimumChildCount(1)));
    }

    // Test 3: Vacation search bar is displayed
    @Test
    public void vacationSearchBar_isDisplayed() {
        onView(withId(R.id.report_search_vacation))
                .check(matches(isDisplayed()));
    }

//    // Test 4: Excursion search bar is displayed
//    @Test
//    public void excursionSearchBar_isDisplayed() {
//        onView(withId(R.id.report_search_excursion))
//                .check(matches(isDisplayed()));
//    }

    // Test 5: Typing a valid vacation name filters the list to at least 1 result
    @Test
    public void vacationSearch_withValidName_showsResults() {
        onView(withId(R.id.report_search_vacation))
                .perform(typeText("Bahamas"));

        onView(withId(R.id.report_recycler_view))
                .check(matches(hasMinimumChildCount(1)));
    }

    // Test 6: Clearing the vacation search restores the full list
    @Test
    public void vacationSearch_afterClearing_restoresFullList() {
        // Type to filter
        onView(withId(R.id.report_search_vacation))
                .perform(typeText("Bahamas"));

        // Clear the search
        onView(withId(R.id.report_search_vacation))
                .perform(clearText());

        // Full list should return (at least 1 row)
        onView(withId(R.id.report_recycler_view))
                .check(matches(hasMinimumChildCount(1)));
    }

    // Test 7: Column header row is visible
    @Test
    public void columnHeaders_areDisplayed() {
        onView(withId(R.id.report_column_name))
                .check(matches(isDisplayed()));
        onView(withId(R.id.report_column_hotel))
                .check(matches(isDisplayed()));
        onView(withId(R.id.report_column_start))
                .check(matches(isDisplayed()));
        onView(withId(R.id.report_column_end))
                .check(matches(isDisplayed()));
        onView(withId(R.id.report_column_excursion_count))
                .check(matches(isDisplayed()));
    }
}