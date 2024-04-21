package com.minkyu;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

public class Main {
    static {
        File logRoot = new File("logs");

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            if (!logRoot.isDirectory()) {
                System.out.println(logRoot.getAbsolutePath() + " is not a directory");
                return;
            }

            for (File file : logRoot.listFiles()) {
                System.out.println("Deleting " + file.getAbsolutePath());
                if (!file.delete()) {
                    System.out.println("Failed to delete " + file.getAbsolutePath());
                }
            }
        }));
    }

    public static void main(String[] args) throws InterruptedException {
        Logger logger = LogManager.getLogger("MyLogger");

        while (true) {
            logger.info(_1KBString());
            Thread.sleep(1000);
        }
    }

    private static String _1KBString() {
        StringBuilder sb = new StringBuilder();
        sb.append("a".repeat(1024));
        return sb.toString();
    }
}
