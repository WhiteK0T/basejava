package ru.javawebinar.basejava.model;

import java.util.*;

/**
 * gkislin
 * 14.07.2016
 */
public class ListSection extends Section {
    private final List<String> items;

    public ListSection(List<String> items) {
        Objects.requireNonNull(items, "items must not be null");
        this.items = items;
    }

    public ListSection(String... items) {
        this(Arrays.asList(items));
    }

    public ListSection(ListSection copy) {
        List<String> list = new ArrayList<>();
        for (String item : copy.items) {
            if (item != null) {
                list.add(new String(item.toCharArray()));
            } else list.add(null);
        }
        this.items = list;
    }

    public List<String> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return items.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ListSection that = (ListSection) o;

        return items.equals(that.items);

    }

    @Override
    public int hashCode() {
        return items.hashCode();
    }
}