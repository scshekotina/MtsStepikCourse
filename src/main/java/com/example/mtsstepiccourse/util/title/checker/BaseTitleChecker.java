package com.example.mtsstepiccourse.util.title.checker;

public class BaseTitleChecker implements TitleChecker {

    protected TitleChecker next;

    @Override
    public void setNext(TitleChecker next) {
        this.next = next;
    }

    @Override
    public boolean checkTitle(CharSequence value) {
        if (next != null) {
            return next.checkTitle(value);
        }
        return true;
    }
}
