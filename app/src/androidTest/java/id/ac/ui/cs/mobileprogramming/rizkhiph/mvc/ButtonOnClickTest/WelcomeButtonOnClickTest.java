package id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.ButtonOnClickTest;

import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.R;
import id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.activity.implementation.SearchActivity;
import id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.activity.implementation.WelcomeActivity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class WelcomeButtonOnClickTest {
    @Rule
    public IntentsTestRule<WelcomeActivity> activityRule =
            new IntentsTestRule<>(WelcomeActivity.class);

    @Test
    public void textWelcomeButtonOnClickTest() {
        onView(withId(R.id.search_page_button)).perform(click());

        intended(hasComponent(SearchActivity.class.getName()));
    }
}
