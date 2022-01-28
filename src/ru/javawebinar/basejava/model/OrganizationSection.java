package ru.javawebinar.basejava.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * gkislin
 * 19.07.2016
 */
public class OrganizationSection extends Section {
    private final List<Organization> organizations;

    public OrganizationSection(List<Organization> organizations) {
        Objects.requireNonNull(organizations, "organizations must not be null");
        this.organizations = organizations;
    }

    public OrganizationSection(Organization... organizations) {
        this(Arrays.asList(organizations));
    }

    public OrganizationSection(OrganizationSection copy) {
        List<Organization> list = new ArrayList<>();
        for (Organization item : copy.getOrganizations()) {
            if (item != null) {
                list.add(new Organization(item));
            } list.add(null);
        }
        this.organizations = list;
    }

    public List<Organization> getOrganizations() {
        return organizations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrganizationSection that = (OrganizationSection) o;

        return organizations.equals(that.organizations);

    }

    @Override
    public int hashCode() {
        return organizations.hashCode();
    }

    @Override
    public String toString() {
        return organizations.toString();
    }
}