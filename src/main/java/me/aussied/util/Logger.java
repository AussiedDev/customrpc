package me.aussied.util;

import me.aussied.Presence;

/** Custom RPC.
 *  @author Aussied!
 */
public class Logger {

    public static void printInfo(Object line) {
        System.out.printf("[%s/INFO] " + line, Presence.instance.name + "\n");
    }

    public static void printError(Object line) {
        System.out.printf("[%s/ERROR] " + line, Presence.instance.name + "\n");
    }

}
