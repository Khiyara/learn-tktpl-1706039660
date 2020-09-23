package id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.Lab2.TextOnViewTest;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.Lab2.activity.implementation.SearchActivity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class SearchActivityTest {
    @Rule
    public ActivityScenarioRule<SearchActivity> activityRule =
            new ActivityScenarioRule<>(SearchActivity.class);

    @Test
    public void textWelcomeOnViewTest() {
        onView(withText("Manga / Anime")).check(matches(isDisplayed()));
        onView(withText("Search")).check(matches(isDisplayed()));
    }
}

