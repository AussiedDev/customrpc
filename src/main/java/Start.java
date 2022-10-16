import me.aussied.Presence;
import org.json.simple.parser.ParseException;

import java.io.IOException;

/** Custom RPC.
 *  @author Aussied!
 */
public class Start {
    /** Might use JavaFX in the future, and maybe as a separate branch */

    /** Main function to get the application running */
    public static void main(String[] args) throws IOException, ParseException {
        /** Initialize Presence */
        Presence.instance.init(args);

        /** Calls Presence's stop() function to disable RPC */
        Runtime.getRuntime().addShutdownHook(new Thread(() -> Presence.instance.stop()));
    }

}
