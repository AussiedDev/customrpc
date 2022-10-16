package me.aussied.config;

import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

/** Custom RPC.
 *  @author Aussied!
 */
public class JsonConfig {

    /** Writes a specified JSON object at a specified location */
    public static void write(String location, JSONObject obj) {
        try (FileWriter writer = new FileWriter(location)) {
            writer.write(obj.toString());
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
