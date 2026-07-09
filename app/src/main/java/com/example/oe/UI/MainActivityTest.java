package com.example.oe.UI;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.isNotEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.oe.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    // Launches MainActivity fresh before each test
    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);

    // Test 1: Button should be disabled when the screen first loads
    @Test
    public void enterButton_isDisabledOnLaunch() {
        onView(withId(R.id.button))
                .check(matches(isNotEnabled()));
    }

    // Test 2: Button should stay disabled with a valid email but no password
    @Test
    public void enterButton_disabledWithEmailOnly() {
        onView(withId(R.id.editTextEmailAddress))
                .perform(typeText("test@example.com"));

        onView(withId(R.id.button))
                .check(matches(isNotEnabled()));
    }

    // Test 3: Button should stay disabled with a password but no email
    @Test
    public void enterButton_disabledWithPasswordOnly() {
        onView(withId(R.id.editTextPassword))
                .perform(typeText("mypassword"));

        onView(withId(R.id.button))
                .check(matches(isNotEnabled()));
    }

    // Test 4: Button should stay disabled with an invalid email format
    @Test
    public void enterButton_disabledWithInvalidEmail() {
        onView(withId(R.id.editTextEmailAddress))
                .perform(typeText("notanemail"));
        onView(withId(R.id.editTextPassword))
                .perform(typeText("mypassword"));

        onView(withId(R.id.button))
                .check(matches(isNotEnabled()));
    }

    // Test 5: Button should become enabled with a valid email AND a password
    @Test
    public void enterButton_enabledWithValidEmailAndPassword() {
        onView(withId(R.id.editTextEmailAddress))
                .perform(typeText("test@example.com"));
        onView(withId(R.id.editTextPassword))
                .perform(typeText("mypassword"));

        onView(withId(R.id.button))
                .check(matches(isEnabled()));
    }

    // Test 6: Button should go back to disabled if email is cleared after being valid
    @Test
    public void enterButton_disabledAfterClearingEmail() {
        onView(withId(R.id.editTextEmailAddress))
                .perform(typeText("test@example.com"));
        onView(withId(R.id.editTextPassword))
                .perform(typeText("mypassword"));

        // Confirm it was enabled first
        onView(withId(R.id.button))
                .check(matches(isEnabled()));

        // Now clear the email
        onView(withId(R.id.editTextEmailAddress))
                .perform(clearText());

        // Button should be disabled again
        onView(withId(R.id.button))
                .check(matches(isNotEnabled()));
    }
}