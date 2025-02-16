package se.lex.utils;

import se.lex.sequencer.AppUserIdSequencer;
import se.lex.sequencer.PersonIdSequencer;
import se.lex.sequencer.TodoItemIdSequencer;

import java.io.*;
import java.util.Properties;

public class SequencerUtils {
    // Update the path to point to your directory
    private static final String PROPERTIES_FILE = "C:/Users/ramis/OneDrive/Desktop/Lexicon cors/java/Todo-App/src/main/resources/todo_app/sequencers.properties";

    public static void saveSequencerValues() {
        Properties properties = new Properties();
        properties.setProperty("appUserSequencer", String.valueOf(AppUserIdSequencer.getCurrentId()));
        properties.setProperty("personSequencer", String.valueOf(PersonIdSequencer.getCurrentId()));
        properties.setProperty("todoItemSequencer", String.valueOf(TodoItemIdSequencer.getCurrentId()));

        try (OutputStream output = new FileOutputStream(PROPERTIES_FILE)) {
            properties.store(output, "Sequencer Values");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadSequencerValues() {
        Properties properties = new Properties();
        File file = new File(PROPERTIES_FILE);

        // Create the file if it doesn't exist
        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs(); // Ensure the directory exists
                file.createNewFile();
                // Initialize default values
                properties.setProperty("appUserSequencer", "0");
                properties.setProperty("personSequencer", "0");
                properties.setProperty("todoItemSequencer", "0");

                // Save the default values to the file
                try (OutputStream output = new FileOutputStream(PROPERTIES_FILE)) {
                    properties.store(output, "Default Sequencer Values");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Load the values from the file
        try (InputStream input = new FileInputStream(PROPERTIES_FILE)) {
            properties.load(input);
            AppUserIdSequencer.setCurrentId(Integer.parseInt(properties.getProperty("appUserSequencer")));
            PersonIdSequencer.setCurrentId(Integer.parseInt(properties.getProperty("personSequencer")));
            TodoItemIdSequencer.setCurrentId(Integer.parseInt(properties.getProperty("todoItemSequencer")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
