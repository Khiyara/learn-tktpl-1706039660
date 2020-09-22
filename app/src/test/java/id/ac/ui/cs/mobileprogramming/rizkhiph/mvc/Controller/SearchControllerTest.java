package id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.Controller;

import org.junit.Test;

import id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.activity.SearchControllerListener;
import id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.controller.SearchController;
import id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.view.SearchView;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;

public class SearchControllerTest {

    SearchController searchController = new SearchController(mock(SearchView.class), mock(SearchControllerListener.class));
    @Test
    public void loginWithCorrectCreds() {
        this.searchController.setState(true);
        assertThat(this.searchController.getState(), equalTo("Manga"));
    }

    @Test
    public void loginWithFalseEmail() {
        this.searchController.setState(false);
        assertThat(this.searchController.getState(), equalTo("Anime"));
    }
}
