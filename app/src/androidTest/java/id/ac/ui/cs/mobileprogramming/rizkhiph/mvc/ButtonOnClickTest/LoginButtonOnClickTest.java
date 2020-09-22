package id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.ButtonOnClickTest;

import androidx.test.espresso.intent.matcher.IntentMatchers;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.R;
import id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.activity.implementation.LoginActivity;
import id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.activity.implementation.WelcomeActivity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class LoginButtonOnClickTest {
    @Rule
    public IntentsTestRule<LoginActivity> activityRule =
            new IntentsTestRule<>(LoginActivity.class);

    @Test
    public void textLoginButtonOnClickTest() {
        onView(withId(R.id.email_login)).perform(typeText("anything@valid.email"));
        onView(withId(R.id.password)).perform(typeText("thisisvalidPassword123"));
        onView(withText("thisisvalidPassword123")).check(matches(isDisplayed()));
        onView(withText("anything@valid.email")).check(matches(isDisplayed()));
        onView(withId(R.id.sign_in_button)).perform(click());

        intended(hasComponent(WelcomeActivity.class.getName()));
    }
}