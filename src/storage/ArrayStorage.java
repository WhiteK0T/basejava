package storage;

import model.Resume;

import java.util.Arrays;

import static java.util.Arrays.fill;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private final Resume[] storage = new Resume[10000];
    private int size = 0;

    public void clear() {
        fill(storage, 0, size,null);
        size = 0;
    }

    public void update(Resume resume) {
        int pos = find(resume.getUuid());
        if (pos == -1) {
            System.out.println("Resume not update");
            return;
        }
        storage[pos] = resume;
    }

    public void save(Resume resume) {
        if (find(resume.getUuid()) != -1) {
            System.out.println("Resume not save");
            return;
        }
        if (size > storage.length) {
            System.out.println("Storage full! Resume not save!");
            return;
        }
        storage[size++] = resume;
    }

    public Resume get(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return storage[i];
            }
        }
        System.out.println("Resume not storage");
        return null;
    }

    public void delete(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                System.arraycopy(storage, i + 1, storage, i, size - i);
                storage[--size] = null;
                return;
            }
        }
        System.out.println("Resume not delete");
    }

    public int find(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }
}
