package com.github.romanqed.DiscordMessageParser.Utils;

import java.util.ArrayList;
import java.util.List;

public class Lists {
    public static <T> List<T> multiplyList(List<T> list, int count) {
        count = Math.max(count, 0);
        List<T> result = new ArrayList<>();
        for (int i = 0; i < count; ++i) {
            result.addAll(list);
        }
        return result;
    }
}
