package nl.prbed.hu.aviation.config;

import java.time.Duration;
import java.time.Instant;

public class DataInsertionLoadingBar {
    private static final String FORMAT = "\r%s: \u001B[36m[%s%s]\u001B[0m %s";
    private static final Character LOADING_BAR_BACKGROUND_CHARACTER = '▪';
    private static final Character LOADING_BAR_CHARACTER = '■';
    private static final int LOADING_BAR_SIZE = 20;
    private static final int LOADING_BAR_TITLE_SIZE = 22;
    private static final int LOADING_BAR_STATS_SIZE = 10;

    private final String entityName;
    private final int size;
    private final Instant startTime;

    private int tick;
    private boolean done;

    public DataInsertionLoadingBar(String entityName, int size) {
        this.entityName = entityName;
        this.size = size;
        this.startTime = Instant.now();
        this.tick = 0;
        this.done = false;
    }

    public void load() {
        this.tick++;
        this.print();
    }

    public void done() {
        this.done = true;
        this.print();
    }

    public void clear() {
        System.out.print('\r');
    }

    private void print() {
        System.out.printf(
                FORMAT,
                this.getTitle(),
                this.getLoadingCharacters(),
                this.getBackgroundCharacters(),
                this.getLoadingStats()
        );
    }

    private String getTitle() {
        return this.padString(String.format("Inserting %s", this.entityName), LOADING_BAR_TITLE_SIZE);
    }

    private String getLoadingCharacters() {
        return String.valueOf(LOADING_BAR_CHARACTER).repeat(this.currentBarSize());
    }

    private String getBackgroundCharacters() {
        return String.valueOf(LOADING_BAR_BACKGROUND_CHARACTER).repeat(LOADING_BAR_SIZE - this.currentBarSize());
    }

    private String getLoadingStats() {
        return this.padString(
                String.format(
                        "\u001B[35m%s\u001B[0m / \u001B[35m%s\u001B[0m %s",
                        this.padString(String.valueOf(this.tick), 3),
                        this.padString(String.valueOf(this.size), 3),
                        this.done ? String.format(": \u001B[32mDONE\u001B[0m ~ \033[0;33m%ss\u001B[0m \n", Duration.between(this.startTime, Instant.now()).toSeconds()) : ""
                ),
                LOADING_BAR_STATS_SIZE
        );
    }

    private int currentBarSize() {
        return this.tick * LOADING_BAR_SIZE / this.size;
    }

    private String padString(String string, int padding) {
        return String.format("%-" + padding + "s", string);
    }

    public static DataInsertionLoadingBar create(String entityName, int size) {
        return new DataInsertionLoadingBar(entityName, size);
    }
}
