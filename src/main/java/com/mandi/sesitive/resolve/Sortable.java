package com.mandi.sesitive.resolve;

public interface Sortable {
    int HIGHEST_PRIORITY = -2147483648;
    int LOWEST_PRIORITY = 2147483647;

    int order();
}