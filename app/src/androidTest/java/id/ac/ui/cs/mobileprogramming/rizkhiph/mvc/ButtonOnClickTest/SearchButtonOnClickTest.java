package id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.ButtonOnClickTest;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.R;
import id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.activity.implementation.SearchActivity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.containsString;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class SearchButtonOnClickTest {
    @Rule
    public ActivityScenarioRule<SearchActivity> activityRule =
            new ActivityScenarioRule<>(SearchActivity.class);

    @Test
    public void textSearchButtonOnClickTest() {
        onView(withId(R.id.text_to_search)).perform(typeText("One Piece"));
        onView(withText("One Piece")).check(matches(isDisplayed()));
        onView(withId(R.id.search_button)).perform(click());

        onView(withId(R.id.textView_fetched_results)).check(matches(withText(containsString("One Piece"))));
        onView(withId(R.id.textView_fetched_results)).check(matches(withText(containsString("myanimelist.net"))));
    }
}
