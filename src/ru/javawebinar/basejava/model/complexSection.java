package ru.javawebinar.basejava.model;

import java.util.List;

public class complexSection implements Section {
    private final List<InfoAbout> abouts;

    public complexSection(List<InfoAbout> abouts) {
        this.abouts = abouts;
    }

    public List<InfoAbout> getAbouts() {
        return abouts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        complexSection that = (complexSection) o;

        return abouts != null ? abouts.equals(that.abouts) : that.abouts == null;
    }

    @Override
    public int hashCode() {
        return abouts != null ? abouts.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "complexSection{" +
                "abouts=" + abouts +
                '}';
    }
}
