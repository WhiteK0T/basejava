package ru.javawebinar.basejava.model;

public enum ContactType {
    PHONE("Тел."),
    MOBILE("Мобильный"),
    TELEGRAM("Телеграм"),
    VK("Вконтакте"),
    MAIL("Почта"),
    LINKEDIN("Профиль LinkedIn"),
    GITHUB("Профиль GitHub"),
    STATCKOVERFLOW("Профиль Stackoverflow"),
    HOME_PAGE("Домашняя страница");

    private final String title;

    ContactType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}