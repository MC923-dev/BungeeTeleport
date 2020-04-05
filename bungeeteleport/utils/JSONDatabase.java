package bungeeteleport.utils;

import com.google.common.base.Preconditions;
import net.md_5.bungee.api.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JSONDatabase {
    private transient File file;

    public final void setFile(File file) {
        this.file = file;
    }

    public final File getFile() {
        return this.file;
    }

    public static <T, V> Map<T, V> fastMap(List<T> keys, List<V> values) {
        Preconditions.checkNotNull(keys);
        Preconditions.checkNotNull(values);
        Preconditions.checkArgument((keys.size() == values.size()));
        HashMap<T, V> map = new HashMap<>();
        for (int i = 0; i < keys.size(); ++i) {
            map.put(keys.get(i), values.get(i));
        }
        return map;
    }

    public static <T, V> Map<T, V> fastSingleMap(T key, V value) {
        HashMap<T, V> map = new HashMap<T, V>();
        map.put(key, value);
        return map;
    }

    public static <T extends JSONDatabase> T load(File file, Class<T> clazz) {
        T t = GsonUtils.GSON.fromJson(GsonUtils.readFile(file), clazz);
        t.setFile(file);
        t.init();
        return t;
    }

    public static <T extends JSONDatabase> T load(Plugin p, String fileName, Class<T> clazz) {
        File file = new File(p.getDataFolder(), fileName);
        if (file.exists() && file.length() != 0L) {
            T t = GsonUtils.GSON.fromJson(GsonUtils.readFile(file), clazz);
            t.setFile(file);
            t.init();
            return t;
        }
        p.getDataFolder().mkdirs();
        try {
            JSONDatabase t = clazz.newInstance();
            t.setFile(file);
            t.save();
            t.init();
            return (T)t;
        }
        catch (Exception var5) {
            var5.printStackTrace();
            return null;
        }
    }

    public void init() {
    }

    public void save() {
        try {
            Files.write(this.file.toPath(), GsonUtils.GSON.toJson(this).getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        }
        catch (IOException var2) {
            var2.printStackTrace();
        }
    }
}