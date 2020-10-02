package id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.service;

import android.util.Log;

import id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.activity.SearchControllerListener;
import id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.common.RequestApi;
import id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.common.RequestInterface;
import id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.constant.ApiConstants;

public class SearchService implements RequestInterface {
    private final String TAG = "Search Service";
    public final int SEARCH = 1;
    public final int DETAIL = 2;

    private RequestApi requestApi = new RequestApi();
    private SearchControllerListener listener;

    public SearchService(SearchControllerListener listener) {
        this.listener = listener;
    }

    public void searchAnime(String name) {
        Log.i(TAG, "[+] Searching Anime " + name);
        String url = ApiConstants.SEARCH_ANIME_API + "?q=" + name;
        this.getRequestApi().getRequest(url, this, SEARCH);
    }

    public void searchManga(String name) {
        Log.i(TAG, "[+] Searching Manga " + name);
        String url = ApiConstants.SEARCH_MANGA_API + "?q=" + name;
        this.getRequestApi().getRequest(url, this, SEARCH);
    }

    public void detailAnime(String id) {
        Log.i(TAG, "[+] Detail Anime");
        String url = ApiConstants.DETAIL_ANIME_API + "/" + id;
        this.getRequestApi().getRequest(url, this, DETAIL);
    }

    public void detailManga(String id) {
        Log.i(TAG, "[+] Detail Manga");
        String url = ApiConstants.DETAIL_MANGA_API + "/" + id;
        this.getRequestApi().getRequest(url, this, DETAIL);
    }

    @Override
    public void setResponseText(String result, int type) {
        if (type == SEARCH) {
            this.getListener().onGetSearch(result);
        } else if (type == DETAIL) {
            this.getListener().onGetDetail(result);
        }
    }

    public RequestApi getRequestApi() {
        return this.requestApi;
    }

    public SearchControllerListener getListener() {
        return this.listener;
    }
}
