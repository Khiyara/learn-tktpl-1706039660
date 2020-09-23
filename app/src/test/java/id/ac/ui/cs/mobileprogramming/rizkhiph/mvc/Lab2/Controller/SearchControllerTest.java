package id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.Lab2.Controller;

import android.view.View;
import android.widget.CompoundButton;

import org.junit.Test;

import id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.Lab2.activity.SearchControllerListener;
import id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.Lab2.controller.SearchController;
import id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.Lab2.service.SearchService;
import id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.Lab2.view.SearchView;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SearchControllerTest {
    SearchService searchService = mock(SearchService.class);
    SearchController searchController = new SearchController(mock(SearchView.class), mock(SearchControllerListener.class)) {
        @Override
        public SearchService getSearchService() {
            return searchService;
        }
    };
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

    @Test
    public void onSearchButtonClickSearchAnime() {
        when(this.searchController.getSearchView().getText()).thenReturn("One Piece");
        this.searchController.setState(false);
        this.searchController.onClick(mock(View.class));
        verify(this.searchController.getSearchView(), times(1)).getText();
        verify(this.searchController.getSearchService(), times(1)).searchAnime("One Piece");
        verify(this.searchController.getListener(), times(1)).onSearch();
    }

    @Test
    public void onSearchButtonClickSearchManga() {
        when(this.searchController.getSearchView().getText()).thenReturn("One Piece");
        this.searchController.setState(true);
        this.searchController.onClick(mock(View.class));
        verify(this.searchController.getSearchView(), times(1)).getText();
        verify(this.searchController.getSearchService(), times(1)).searchManga("One Piece");
        verify(this.searchController.getListener(), times(1)).onSearch();
    }

    @Test
    public void onSwitchCompatClicked() {
        this.searchController.onCheckedChanged(mock(CompoundButton.class), true);
        verify(this.searchController.getListener(), times(1)).onStateChange("Manga");
    }

    @Test
    public void getObjectSearchService() {
        SearchController searchController = new SearchController(mock(SearchView.class), mock(SearchControllerListener.class));
        SearchService service = searchController.getSearchService();
    }
}
