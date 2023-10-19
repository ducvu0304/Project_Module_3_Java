package business.utils;

import business.config.IOFile;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class IdGenerator {
    private static Set<Integer> generatedIds = new HashSet<>();


    static {
        loadGeneratedIds();
    }

    public static int generateUniqueId() {
        int id;
        do {
            id = autoId();
        } while (generatedIds.contains(id));
        generatedIds.add(id);
        saveGeneratedIds(); // Save the updated IDs
        return id;
    }

    private static int autoId() {
        return (int) (Math.random() * 999999999);
    }

    private static void saveGeneratedIds() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(IOFile.getInstance().getID_PATH()))) {
            for (int id : generatedIds) {
                writer.println(id);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void loadGeneratedIds() {
        try (BufferedReader reader = new BufferedReader(new FileReader(IOFile.getInstance().getID_PATH()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                int id = Integer.parseInt(line);
                generatedIds.add(id);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

