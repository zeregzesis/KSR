package ksr.grupa3;

import lombok.Data;

@Data
public class Article {

    private String contents;
    private Places label;

    public Article(String contents, Places label) {
        this.contents = contents;
        this.label = label;
    }
}