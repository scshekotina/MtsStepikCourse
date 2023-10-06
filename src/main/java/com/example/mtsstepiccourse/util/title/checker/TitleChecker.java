package com.example.mtsstepiccourse.util.title.checker;

public interface TitleChecker {
    void setNext(TitleChecker next);
    boolean checkTitle(CharSequence value);
}
