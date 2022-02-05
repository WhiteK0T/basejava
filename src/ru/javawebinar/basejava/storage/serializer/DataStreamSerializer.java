package ru.javawebinar.basejava.storage.serializer;

import ru.javawebinar.basejava.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataStreamSerializer implements StreamSerializer {

    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(r.getUuid());
            dos.writeUTF(r.getFullName());
            Map<ContactType, String> contacts = r.getContacts();
            dos.writeInt(contacts.size());
            for (Map.Entry<ContactType, String> entry : contacts.entrySet()) {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            }
            Map<SectionType, Section> sections = r.getSections();
            dos.writeInt(sections.size());
            for (Map.Entry<SectionType, Section> entry : sections.entrySet()) {
                SectionType type = entry.getKey();
                Section section = entry.getValue();
                dos.writeUTF(type.name());
                switch (type) {
                    case PERSONAL, OBJECTIVE -> dos.writeUTF(((TextSection) section).getContent());
                    case ACHIEVEMENT, QUALIFICATIONS -> {
                        List<String> items = ((ListSection) section).getItems();
                        dos.writeInt(items.size());
                        for (String item : items) {
                            dos.writeUTF(item);
                        }
                    }
                    case EXPERIENCE, EDUCATION -> {
                        List<Organization> organizations = ((OrganizationSection) section).getOrganizations();
                        dos.writeInt(organizations.size());
                        for (Organization organization : organizations) {
                            dos.writeUTF(organization.getHomePage().getName());
                            dos.writeUTF(organization.getHomePage().getUrl());
                            List<Organization.Position> positions = organization.getPositions();
                            dos.writeInt(positions.size());
                            for (Organization.Position position : positions) {
                                writeLocalDate(dos, position.getStartDate());
                                writeLocalDate(dos, position.getEndDate());
                                dos.writeUTF(position.getTitle());
                                dos.writeUTF(position.getDescription());
                            }
                        }
                    }
                }
            }
        }
    }

    private void writeLocalDate(DataOutputStream dos, LocalDate localDate) throws IOException {
        dos.writeInt(localDate.getYear());
        dos.writeInt(localDate.getMonth().getValue());
        dos.writeInt(localDate.getDayOfMonth());
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            int size = dis.readInt();
            for (int i = 0; i < size; i++) {
                resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF());
            }
            size = dis.readInt();
            for (int i = 0; i < size; i++) {
                SectionType type = SectionType.valueOf(dis.readUTF());
                Section section = switch (type) {
                    case PERSONAL, OBJECTIVE -> new TextSection(dis.readUTF());
                    case ACHIEVEMENT, QUALIFICATIONS -> {
                        int length = dis.readInt();
                        ArrayList<String> list = new ArrayList<>();
                        for (int j = 0; j < length; j++) {
                            list.add(dis.readUTF());
                        }
                        yield new ListSection(list);
                    }
                    case EXPERIENCE, EDUCATION -> {
                        ArrayList<Organization> organizations = new ArrayList<>();
                        int lengthOrganizations = dis.readInt();
                        for (int j = 0; j < lengthOrganizations; j++) {
                            Link link = new Link(dis.readUTF(), dis.readUTF());
                            ArrayList<Organization.Position> listPositions = new ArrayList<>();
                            int lengthPosition = dis.readInt();
                            for (int k = 0; k < lengthPosition; k++) {
                                LocalDate startDate = LocalDate.of(dis.readInt(), dis.readInt(), dis.readInt());
                                LocalDate endDate = LocalDate.of(dis.readInt(), dis.readInt(), dis.readInt());
                                listPositions.add(new Organization.Position(startDate, endDate, dis.readUTF(), dis.readUTF()));
                            }
                            organizations.add(new Organization(link, listPositions));
                        }
                        yield new OrganizationSection(organizations);
                    }
                };
                resume.addSection(type, section);
            }
            return resume;
        }
    }
}