package Models;

public class FavoriteItem {
    private final String id;
    private final String path;
    private final String keyword;
    private final String searchType;

    public FavoriteItem(String id,String path, String keyword, String searchType) {
        this.id = id;
        this.path = path;
        this.keyword = keyword;
        this.searchType = searchType;
    }

    public String getId() {
        return id;
    }

    public String getPath() {
        return path;
    }

    public String getKeyword() {
        return keyword;
    }

    public String getSearchType() {
        return searchType;
    }
}
