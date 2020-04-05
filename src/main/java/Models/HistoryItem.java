package Models;

import javafx.beans.property.SimpleStringProperty;

public class HistoryItem {
    private final SimpleStringProperty id;
    private final SimpleStringProperty keyword;
    private final SimpleStringProperty type;
    private final SimpleStringProperty directory;

    public HistoryItem(String id, String keyword, String type, String directory) {
        this.id = new SimpleStringProperty(id);
        this.keyword = new SimpleStringProperty(keyword);
        this.type = new SimpleStringProperty(type);
        this.directory = new SimpleStringProperty(directory);
    }

    public String getId() {
        return id.get();
    }

    public String getKeyword() {
        return keyword.get();
    }

    public String getType() {
        return type.get();
    }

    public String getDirectory() {
        return directory.get();
    }
}
