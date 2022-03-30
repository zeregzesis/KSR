package ksr.grupa3;

import java.util.stream.Stream;

public enum Places {
    westgermany(0),
    usa(1),
    france(2),
    uk(3),
    canada(4),
    japan(5);

    public final int label;

    private Places(int label) {
        this.label = label;
    }

    public static Stream<Places> stream() {
        return Stream.of(Places.values());
    }
}
