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

import com.example.newapplication.tools.MySolution;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityUITest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);
    ViewInteraction i1, i2, button, out;
    String input1, input2;

    @Before
    public void setUp() {
        i1 = onView(withId(R.id.input1));
        i2 = onView(withId(R.id.input2));
        button = onView(withId(R.id.button));
        out = onView(withId(R.id.out));
    }

    @Test
    public void mainActivityUITest_withValidInputs() {
        input1 = "Quality";
        input2 = "4";
        i1.perform(replaceText(input1), closeSoftKeyboard());
        i2.perform(replaceText(input2), closeSoftKeyboard());
        button.perform(click());
        MySolution mySol = new MySolution(input1, Integer.parseInt(input2));
        try {
            out.check(matches(withText(mySol.findSuffix())));
        }
        catch (Exception ex) {
            out.check(matches(withText(ex.getMessage())));
        }
    }

    @Test
    public void mainActivityUITest_withZeroCharIndex() {
        input1 = "Quality";
        input2 = "0";
        i1.perform(replaceText(input1), closeSoftKeyboard());
        i2.perform(replaceText(input2), closeSoftKeyboard());
        button.perform(click());
        MySolution mySol = new MySolution(input1, Integer.parseInt(input2));
        try {
            out.check(matches(withText(mySol.findSuffix())));
        }
        catch (Exception ex) {
            out.check(matches(withText(ex.getMessage())));
        }
    }

    @Test
    public void mainActivityUITest_withNegativeCharIndex() {
        input1 = "Quality";
        input2 = "-6";
        i1.perform(replaceText(input1), closeSoftKeyboard());
        i2.perform(replaceText(input2), closeSoftKeyboard());
        button.perform(click());
        MySolution mySol = new MySolution(input1, Integer.parseInt(input2));
        try {
            out.check(matches(withText(mySol.findSuffix())));
        }
        catch (Exception ex) {
            out.check(matches(withText(ex.getMessage())));
        }
    }

    @Test
    public void mainActivityUITest_withGreaterCharIndex_thanInput1() {
        input1 = "Traveltime";
        input2 = "11";
        i1.perform(replaceText(input1), closeSoftKeyboard());
        i2.perform(replaceText(input2), closeSoftKeyboard());
        button.perform(click());
        MySolution mySol = new MySolution(input1, Integer.parseInt(input2));
        try {
            out.check(matches(withText(mySol.findSuffix())));
        }
        catch (Exception ex) {
            out.check(matches(withText(ex.getMessage())));
        }
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
