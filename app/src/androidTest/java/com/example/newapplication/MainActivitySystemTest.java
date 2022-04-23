package com.example.newapplication;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivitySystemTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);
    ViewInteraction i1, i2, button, out;

    @Before
    public void setUp() {
        i1 = onView(withId(R.id.input1));
        i2 = onView(withId(R.id.input2));
        button = onView(withId(R.id.button));
        out = onView(withId(R.id.out));
    }

    @Test
    // Normal Behaviour
    public void mainActivitySystemTest_withValidInputs() {
        i1.perform(replaceText("premium"), closeSoftKeyboard());
        i2.perform(replaceText("5"), closeSoftKeyboard());
        button.perform(click());
        out.check(matches(withText("emium")));
    }

    @Test
    // Non-number second parameter as input
    public void mainActivitySystemTest_withInput2AsString() {
        i1.perform(replaceText("premium"), closeSoftKeyboard());
        i2.perform(replaceText("five"), closeSoftKeyboard());
        button.perform(click());
        out.check(matches(withText("Character index must be a natural number")));
    }

    @Test
    // The second parameter greater than the length of the first parameter
    public void mainActivitySystemTest_input2_largerThanInput1Length() {
        i1.perform(replaceText("premium"), closeSoftKeyboard());
        i2.perform(replaceText("10"), closeSoftKeyboard());
        button.perform(click());
        out.check(matches(withText("Character index can't be greater than input1 length")));
    }

    @Test
    // First Parameter as Empty String
    public void mainActivitySystemTest_withInput1AsEmptyString() {
        i1.perform(replaceText(""), closeSoftKeyboard());
        i2.perform(replaceText("five"), closeSoftKeyboard());
        button.perform(click());
        out.check(matches(withText("Input1 cannot be empty.")));
    }

    @Test
    // Second Parameter as Empty String
    public void mainActivitySystemTest_withInput2AsEmptyString() {
        i1.perform(replaceText("someinput"), closeSoftKeyboard());
        i2.perform(replaceText(""), closeSoftKeyboard());
        button.perform(click());
        out.check(matches(withText("Character index cannot be empty.")));
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
