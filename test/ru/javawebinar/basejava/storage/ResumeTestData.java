package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.*;

import java.time.Month;

public class ResumeTestData {

    private static int count = 1;
    private static final Resume RESUME_1 = createResume();
    private static final Resume RESUME_2 = createResume();
    private static final Resume RESUME_3 = createResume();
    private static final Resume RESUME_4 = createResume();

    public static Resume createResume(String uuid, String fullName) {
        return new Resume(uuid, fullName);
    }

    public static Resume createResume() {
        Resume resume = new Resume("uuid" + count, "name" + count);
        genSectionAndContact(resume);
        count++;
        return resume;
    }

    public static Resume getResume(int nomerResume) {
        return switch (nomerResume) {
            case 1 -> new Resume(RESUME_1);
            case 2 -> new Resume(RESUME_2);
            case 3 -> new Resume(RESUME_3);
            case 4 -> new Resume(RESUME_4);
            default -> null;
        };
    }

    private static void genSectionAndContact(Resume resume) {
        resume.addContact(ContactType.MAIL, "mail" + count + "@ya.ru");
        resume.addContact(ContactType.PHONE, "77777" + count);
        resume.addSection(SectionType.OBJECTIVE, new TextSection("Objective" + count));
        resume.addSection(SectionType.PERSONAL, new TextSection("Personal data" + count));
        resume.addSection(SectionType.ACHIEVEMENT, new ListSection("Achivment1" + count, "Achivment1" + count, "Achivment1" + count));
        resume.addSection(SectionType.QUALIFICATIONS, new ListSection("Java", "SQL", "JavaScript"));
        resume.addSection(SectionType.EXPERIENCE,
                new OrganizationSection(
                        new Organization("Organization1" + count, "http://Organization"  + count + "1.ru",
                                new Organization.Position(2005, Month.JANUARY, "position1", "content1"),
                                new Organization.Position(2001, Month.MARCH, 2005, Month.JANUARY, "position2", "content2"))));
        resume.addSection(SectionType.EDUCATION,
                new OrganizationSection(
                        new Organization("Institute", null,
                                new Organization.Position(1996, Month.JANUARY, 2000, Month.DECEMBER, "aspirant", null),
                                new Organization.Position(2001, Month.MARCH, 2005, Month.JANUARY, "student", "IT facultet")),
                        new Organization("Organization12", "http://Organization12.ru")));
        resume.addSection(SectionType.EXPERIENCE,
                new OrganizationSection(
                        new Organization("Organization2", "http://Organization2.ru",
                                new Organization.Position(2015, Month.JANUARY, "position1", "content1"))));
    }
}