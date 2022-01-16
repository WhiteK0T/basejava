package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapUuidStorage extends AbstractStorage {
    private Map<String, Resume> map = new HashMap<>();

    @Override
    protected String getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected void doUpdate(Resume r, Object uuid) {
        map.put(String.valueOf(uuid), r);
    }

    @Override
    protected boolean isExist(Object uuid) {
        return map.containsKey(String.valueOf(uuid));
    }

    @Override
    protected void doSave(Resume r, Object uuid) {
        map.put(String.valueOf(uuid), r);
    }

    @Override
    protected Resume doGet(Object uuid) {
        return map.get(String.valueOf(uuid));
    }

    @Override
    protected void doDelete(Object uuid) {
        map.remove(String.valueOf(uuid));
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    protected List<Resume> doAll() {
        return new ArrayList<>(map.values());
    }

    @Override
    public int size() {
        return map.size();
    }
}