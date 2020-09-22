package id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.service;

import android.util.Log;

import id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.activity.SearchControllerListener;
import id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.common.RequestApi;
import id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.common.RequestInterface;
import id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.constant.ApiConstants;

public class SearchService implements RequestInterface {
    private final String TAG = "Search Service";
    private RequestApi requestApi = new RequestApi();

    private SearchControllerListener listener;

    public SearchService(SearchControllerListener listener) {
        this.listener = listener;
    }

    public void searchAnime(String name) {
        Log.i(TAG, "[+] Searching Anime");
        String url = ApiConstants.SEARCH_ANIME_API + "?q=" + name;
        this.getRequestApi().getRequest(url, this);
    }

    public void searchManga(String name) {
        Log.i(TAG, "[+] Searching Manga");
        String url = ApiConstants.SEARCH_MANGA_API + "?q=" + name;
        this.getRequestApi().getRequest(url, this);
    }

    public String formatString(String text){
        StringBuilder json = new StringBuilder();
        String indentString = "";
        for (int i = 0; i < text.length(); i++) {
            char letter = text.charAt(i);
            switch (letter) {
                case '{':
                case '[':
                    json.append("\n").append(indentString).append(letter).append("\n");
                    indentString = indentString + "\t";
                    json.append(indentString);
                    break;
                case '}':
                case ']':
                    indentString = indentString.replaceFirst("\t", "");
                    json.append("\n").append(indentString).append(letter);
                    break;
                case ',':
                    json.append(letter).append("\n").append(indentString);
                    break;
                default:
                    json.append(letter);
                    break;
            }
        }
        return json.toString();
    }

    // TODO() implement parse JSON to Readable
    @Override
    public void setResponseText(String result) {
        listener.onGetResponse(this.formatString(result));
    }

    public RequestApi getRequestApi() {
        return this.requestApi;
    }
}
