package bungeeteleport.utils;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

public class GsonUtils {
    public static Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().enableComplexMapKeySerialization().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).setLenient().create();

    public static void writeToFile(File file, String str) {
        try {
            Files.write(file.toPath(), str.getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        }
        catch (IOException var3) {
            var3.printStackTrace();
        }
    }

    public static void writeAsJsonToFile(File file, Object obj) {
        GsonUtils.writeToFile(file, GSON.toJson(obj));
    }

    public static String readFile(File file) {
        try {
            return new String(Files.readAllBytes(file.toPath()));
        }
        catch (IOException var2) {
            var2.printStackTrace();
            return null;
        }
    }
}