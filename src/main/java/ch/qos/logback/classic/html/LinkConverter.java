package ch.qos.logback.classic.html;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.helpers.Transform;

public class LinkConverter extends ClassicConverter {
    public String convert(ILoggingEvent iLoggingEvent) {
        String message = iLoggingEvent.getMessage();
        message = Transform.escapeTags(message);
        message = message.replaceAll(" (/\\S+)", " <a href=\"$1\">file://$1</a>");
        return message;
    }
}
