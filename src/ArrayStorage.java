import java.util.Arrays;

import static java.util.Arrays.fill;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private final Resume[] storage = new Resume[10000];
    private int position = -1;

    void clear() {
        fill(storage, 0, position + 1,null);
        position = -1;
    }

    void save(Resume r) {
        storage[++position] = r;
    }

    Resume get(String uuid) {
        for (int i = 0; i < position; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i < position; i++) {
            if (storage[i].uuid.equals(uuid)) {
                System.arraycopy(storage, i + 1, storage, i, position - i + 1);
                storage[position--] = null;
                break;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, position + 1);
    }

    int size() {
        return position + 1;
    }
}
