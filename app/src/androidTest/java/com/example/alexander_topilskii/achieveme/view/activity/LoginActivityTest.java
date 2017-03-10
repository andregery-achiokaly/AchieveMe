package com.example.alexander_topilskii.achieveme.view.activity;

import android.content.Context;
import android.os.IBinder;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingPolicies;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.Root;
import android.support.test.espresso.idling.CountingIdlingResource;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.alexander_topilskii.achieveme.R;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeUnit;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class LoginActivityTest {

    @Rule
    public ActivityTestRule<LoginActivity> activityTestRule = new ActivityTestRule<>(
            LoginActivity.class);

    @Before
    public void initValidString() {
        // Specify a valid string.
    }

    @Test
    public void onCreate() throws Exception {

    }

    @Test
    public void login() throws Exception {
        //set login
        onView(withId(R.id.input_login_edittext)).perform(typeText("test"));

        //setPassword
        onView(withId(R.id.input_password)).perform(typeText("test"));
        closeSoftKeyboard();

        //press button
        onView(withId(R.id.login_btn)).perform(click());

        // Check that the text was changed.
        LoginActivity activity = activityTestRule.getActivity();

        onView(withText("true"))
                .inRoot(withDecorView(not(activityTestRule.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));
    }






    @Test
    public void registration() throws Exception {

    }

    @Test
    public void authorizationError() throws Exception {

    }

}

