package id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.Service;

import org.junit.Test;

import id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.activity.SearchControllerListener;
import id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.common.RequestApi;
import id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.constant.ApiConstants;
import id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.service.SearchService;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class SearchServiceTest {
    RequestApi requestApi = mock(RequestApi.class);
    SearchService searchService = new SearchService(mock(SearchControllerListener.class)) {
        public RequestApi getRequestApi() {
            return requestApi;
        }
    };

    @Test
    public void searchAnime() {
        this.searchService.searchAnime("One Piece");
        verify(requestApi, times(1)).getRequest(
                ApiConstants.SEARCH_ANIME_API + "?q=" + "One Piece", searchService);
    }

    @Test
    public void searchManga() {
        this.searchService.searchManga("One Piece");
        verify(requestApi, times(1)).getRequest(
                ApiConstants.SEARCH_MANGA_API + "?q=" + "One Piece", searchService);
    }

    @Test
    public void responseToJsonStringify() {
        assertThat(this.searchService.formatString("{}"), equalTo("\n{\n\t\n}"));
    }
}
