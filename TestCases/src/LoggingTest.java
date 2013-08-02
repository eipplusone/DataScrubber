import java.io.IOException;
import java.util.Date;
import java.util.logging.*;

/**
 * @author Ruslan Kiselev
 *         Date: 8/1/13
 *         Time: 10:57 AM
 *         Project Name: DataScrubber
 */
public class LoggingTest {

    private static Logger logger = Logger.getLogger(LoggingTest.class.getName());

    public static void main(String[] args) {

        FileHandler fh = null;
        try {
            fh = new FileHandler("blarun.log", true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        fh.setFormatter(new Formatter() {
            @Override
            public String format(LogRecord record) {
                StringBuffer buf = new StringBuffer(1000);
                buf.append(new Date());
                buf.append(" ");
                buf.append(record.getLevel());
                buf.append(" ");
                buf.append(formatMessage(record));
                buf.append("/n");
                return buf.toString();
            }
        });
        logger.addHandler(fh);

        String string = null;
        try{
            string.charAt(1);
        }
        catch (Exception e){
            logger.log(Level.FINEST, e.getMessage());
        }
    }
}
