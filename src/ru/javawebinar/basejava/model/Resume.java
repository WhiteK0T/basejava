package ru.javawebinar.basejava.model;

import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * com.urise.webapp.model.Resume class
 */
public class Resume implements Comparable<Resume> {

    // Unique identifier
    private final String uuid;

    private final String fullName;

    private final Map<ContactType, String> contacts = new EnumMap<>(ContactType.class);
    private final Map<SectionType, Section> sections = new EnumMap<>(SectionType.class);

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(String uuid, String fullName) {
        Objects.requireNonNull(uuid, "uuid must not be null");
        Objects.requireNonNull(fullName, "fullName must not be null");
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public Resume(Resume copy) {
        this(copy.uuid, copy.fullName);
        for (Map.Entry<ContactType, String> contactTypeStringEntry : copy.getContacts().entrySet()) {
            this.contacts.put(contactTypeStringEntry.getKey(), contactTypeStringEntry.getValue() != null ?
                    String.valueOf(contactTypeStringEntry.getValue().toCharArray()) : null);
        }
        for (Map.Entry<SectionType, Section> sectionTypeSectionEntry : copy.getSections().entrySet()) {
            Section value = sectionTypeSectionEntry.getValue();
            if (value == null) {
                continue;
            }
            if (value instanceof TextSection textSection) {
                sections.put(sectionTypeSectionEntry.getKey(), new TextSection(textSection));
            } else if (value instanceof ListSection listSection) {
                sections.put(sectionTypeSectionEntry.getKey(), new ListSection(listSection));
            } else if (value instanceof OrganizationSection organizationSection) {
                sections.put(sectionTypeSectionEntry.getKey(), new OrganizationSection(organizationSection));
            }
        }
    }

    public String getUuid() {
        return uuid;
    }

    public String getContact(ContactType type) {
        return contacts.get(type);
    }

    public Section getSection(SectionType type) {
        return sections.get(type);
    }

    public Map<ContactType, String> getContacts() {
        return contacts;
    }

    public Map<SectionType, Section> getSections() {
        return sections;
    }

    public void addContact(ContactType type, String value) {
        contacts.put(type, value);
    }

    public void addSection(SectionType sectionType, Section section) {
        sections.put(sectionType, section);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Resume resume = (Resume) o;

        if (!uuid.equals(resume.uuid)) return false;
        return fullName.equals(resume.fullName);

    }

    @Override
    public int hashCode() {
        int result = uuid.hashCode();
        result = 31 * result + fullName.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return uuid + '(' + fullName + ')';
    }

    @Override
    public int compareTo(Resume o) {
        int cmp = fullName.compareTo(o.fullName);
        return cmp != 0 ? cmp : uuid.compareTo(o.uuid);
    }
}