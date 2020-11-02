package nl.prbed.hu.aviation.config;

import java.io.IOException;

@FunctionalInterface
public interface Runnable {
    void run() throws IOException;
}
