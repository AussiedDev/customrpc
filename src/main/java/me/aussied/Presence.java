package me.aussied;

import me.aussied.config.JsonConfig;
import net.arikia.dev.drpc.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;

/** Custom RPC.
 *  @author Aussied!
 */
public class Presence {

    /** Instance thing so Start can access init() and stop() */
    public static final Presence instance = new Presence();

    /** Checks if the applications running */
    private boolean running = true;

    public static final String name = "CustomRPC";

    public void init(String[] args) throws IOException, ParseException {
        String directory = args[0];

        /** Checks if path exists */
        if(new File(directory).exists()) {
            System.out.println("Attempting to run configuration...");

            /** Initializing parser */
            JSONObject jsonObject = (JSONObject) new JSONParser().parse(new FileReader(directory));

            /** Adding settings and initializing them from the configuration file  */
            String imageName = (String) jsonObject.get("imageName"), imageDesc = (String) jsonObject.get("imageDescription"), details = (String) jsonObject.get("details"), state = (String) jsonObject.get("state"), discordId = (String) jsonObject.get("discordId"), timeStamp = (String) jsonObject.get("timeStamp"), smallImageName = (String) jsonObject.get("smallImageName"), imageSmallDesc = (String) jsonObject.get("smallImageDescription");

            /** Initializing handlers */
            DiscordEventHandlers handlers = new DiscordEventHandlers.Builder().setReadyEventHandler(user -> System.out.println("Welcome " + user.username + "#" + user.discriminator + ".")).build();
            DiscordRPC.discordInitialize(discordId, handlers, true);

            /** Lambda thread because we love lambda (runs Discord callbacks) */
            new Thread(() -> { while (running) DiscordRPC.discordRunCallbacks(); }).start();

            /** Initializing a builder to build the RPC */
            DiscordRichPresence.Builder builder = new DiscordRichPresence.Builder(state).setBigImage(imageName, imageDesc).setDetails(details).setSmallImage(smallImageName, imageSmallDesc).setStartTimestamps(timeStamp.equals("true") ? System.currentTimeMillis() : 0);

            /** Updates the RPC to the builder above */
            DiscordRPC.discordUpdatePresence(builder.build());
        } else {
            /** Checks if the second argument equals -c */
            if(args[1].equals("-c"))

                /** Calling to create configuration file */
                createConfig(directory, new JSONObject());
            else

                /** Cant find config :( */
                System.err.println("Cannot find configuration.");
        }
    }

    /** Disable RPC when application exits */
    public void stop() {
        running = false;
        DiscordRPC.discordShutdown();
    }

    /** Adds the settings and writes the file */
    public void createConfig(String location, JSONObject jsonObject) {
        /** Adds objects */
        jsonObject.put("imageName", "large");
        jsonObject.put("imageDescription", "hello there");
        jsonObject.put("details", "this is a line!");
        jsonObject.put("state", "This is another line!");
        jsonObject.put("smallImageName", "large");
        jsonObject.put("smallImageDescription", "hello again");
        jsonObject.put("discordId", "1003186523946090496"); /** id as an example */
        jsonObject.put("timeStamp", "true");

        /** writes configuration */
        JsonConfig.write(location, jsonObject);

        System.out.println("Created new file.");
    }

}
