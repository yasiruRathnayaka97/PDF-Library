package Models;

public class FavoriteItem {
    private final String path;
    private final String keyword;
    private final String searchType;

    public FavoriteItem(String path, String keyword, String searchType) {
        this.path = path;
        this.keyword = keyword;
        this.searchType = searchType;
    }
}
