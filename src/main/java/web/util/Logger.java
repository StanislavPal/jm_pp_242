package web.util;

import org.springframework.stereotype.Component;

public class Logger {
    private boolean doLogging;

    public Logger(boolean doLogging) {
        this.doLogging = doLogging;
    }

    public void info(String action, Object obj) {
        if (doLogging) {
            System.out.println("-----" + action + "+++++");
            System.out.println(obj);
            System.out.println("+++++" + action + "-----");
        }
    }
}
