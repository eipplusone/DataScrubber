package pearson.Utilities;

/**
 * @author Ruslan Kiselev
 *         Date: 8/6/13
 *         Time: 1:47 PM
 *         Project Name: DataScrubber
 */
public class DatabaseUtils {

    public static String createJDBCURL(String URL, String port){
        return "jdbc:mysql://" + URL
                + ":" + port;
    }
}
