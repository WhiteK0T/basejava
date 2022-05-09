package ru.javawebinar.basejava;

import ru.javawebinar.basejava.storage.SqlStorage;
import ru.javawebinar.basejava.storage.Storage;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

public class Config {

    private static final Logger LOG = Logger.getLogger(Config.class.getName());
    private static final String PROPS = "/resumes.properties";

    private static final Config INSTANCE = new Config();

    private final File storageDir;
    private final Storage storage;

    public static Config get() {
        return INSTANCE;
    }

    private Config() {
        try (InputStream is = Config.class.getResourceAsStream(PROPS)) {
            Properties props = new Properties();
            props.load(is);
            storageDir = new File(props.getProperty("storage.dir"));
            storage = getCredentialsFromEnvironmentVariablesOrDefault(props);
        } catch (IOException e) {
            throw new IllegalStateException("Invalid config file " + PROPS);
        }
    }

    public File getStorageDir() {
        return storageDir;
    }

    public Storage getStorage() {
        return storage;
    }

    private Storage getCredentialsFromEnvironmentVariablesOrDefault(Properties props) {
        String databaseUrl = System.getenv("DATABASE_URL");
        if (databaseUrl == null) {
            return new SqlStorage(props.getProperty("db.url"), props.getProperty("db.user"), props.getProperty("db.password"));
        }
        LOG.info("Find DATABASE_URL");
        String[] split = databaseUrl.split("[:@/]");
        String dbUrl = "jdbc:postgresql://" + databaseUrl.split("@")[1];
        return new SqlStorage(dbUrl, split[3], split[4]);
    }

}