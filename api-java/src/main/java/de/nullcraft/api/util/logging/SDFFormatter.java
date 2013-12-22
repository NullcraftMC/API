package de.nullcraft.api.util.logging;

import com.google.common.base.Preconditions;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

/**
 * Format log messages with a date format of {@link java.text.SimpleDateFormat}.
 *
 * @author maxikg <me@maxikg.de>
 */
public class SDFFormatter extends Formatter {

    private final SimpleDateFormat sdf;

    /**
     * Constructor.
     *
     * @throws java.lang.NullPointerException if the given pattern is null
     * @throws java.lang.IllegalArgumentException if the given pattern is invalid
     * @param pattern A {@link java.text.SimpleDateFormat}-Pattern.
     */
    public SDFFormatter(String pattern) {
        this(new SimpleDateFormat(pattern));
    }

    /**
     * Constructor.
     *
     * @throws java.lang.NullPointerException if {@code sdf} is null
     * @param sdf the {@link java.text.SimpleDateFormat} which will be used to format log messages
     */
    public SDFFormatter(SimpleDateFormat sdf) {
        Preconditions.checkNotNull(sdf);

        this.sdf = sdf;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String format(LogRecord record) {
        StringBuilder sb = new StringBuilder();

        sb.append(dateFormat(record.getMillis()));
        sb.append(" [");
        sb.append(record.getLevel().getName());
        sb.append("] ");
        sb.append(formatMessage(record));

        if (record.getThrown() != null) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            record.getThrown().printStackTrace(pw);
            sb.append(sw.toString());
        }

        sb.append('\n');

        return sb.toString();
    }

    /**
     * Formats the date with the given {@link java.text.SimpleDateFormat}.
     *
     * @param millis the time which will be formatted
     * @return the formatted time
     */
    private String dateFormat(long millis) {
        synchronized (sdf) {
            return sdf.format(millis);
        }
    }
}
