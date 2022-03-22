package ksr.grupa3;

import lombok.Data;

@Data
public class Article {

    private final String contents;
    private final Properties props;

    public Article(String contents, Properties props){
        this.contents = contents;
        this.props = props;
    }
}