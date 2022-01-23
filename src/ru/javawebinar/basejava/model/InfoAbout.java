package ru.javawebinar.basejava.model;

import java.net.URL;
import java.time.LocalDate;

public class InfoAbout {
    private final String nameURL;
    private final URL homePage;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final String title;
    private final String description;


    public InfoAbout(String nameURL, URL homePage, LocalDate startDate, LocalDate endDate, String title, String description) {
        this.nameURL = nameURL;
        this.homePage = homePage;
        this.startDate = startDate;
        this.endDate = endDate;
        this.title = title;
        this.description = description;
    }

    public String getNameURL() {
        return nameURL;
    }

    public URL getHomePage() {
        return homePage;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InfoAbout that = (InfoAbout) o;

        if (nameURL != null ? !nameURL.equals(that.nameURL) : that.nameURL != null) return false;
        if (homePage != null ? !homePage.equals(that.homePage) : that.homePage != null) return false;
        if (startDate != null ? !startDate.equals(that.startDate) : that.startDate != null) return false;
        if (endDate != null ? !endDate.equals(that.endDate) : that.endDate != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        return description != null ? description.equals(that.description) : that.description == null;
    }

    @Override
    public int hashCode() {
        int result = nameURL != null ? nameURL.hashCode() : 0;
        result = 31 * result + (homePage != null ? homePage.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "complexSection{" +
                "nameURL='" + nameURL + '\'' +
                ", homePage=" + homePage +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
