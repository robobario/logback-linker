package ch.qos.logback.classic.html;

import ch.qos.logback.classic.html.HTMLLayout;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.CoreConstants;
import ch.qos.logback.core.helpers.Transform;
import ch.qos.logback.core.pattern.Converter;

public class UnsafeHTMLLayout extends HTMLLayout{

    public String doLayout(ILoggingEvent event) {
        StringBuilder buf = new StringBuilder();
        this.startNewTableIfLimitReached(buf);
        boolean odd = true;
        if((this.counter++ & 1L) == 0L) {
            odd = false;
        }

        String level = event.getLevel().toString().toLowerCase();
        buf.append(CoreConstants.LINE_SEPARATOR);
        buf.append("<tr class=\"");
        buf.append(level);
        if(odd) {
            buf.append(" odd\">");
        } else {
            buf.append(" even\">");
        }

        buf.append(CoreConstants.LINE_SEPARATOR);

        for(Converter c = this.head; c != null; c = c.getNext()) {
            this.appendEventToBuffer(buf, c, event);
        }

        buf.append("</tr>");
        buf.append(CoreConstants.LINE_SEPARATOR);
        if(event.getThrowableProxy() != null) {
            this.throwableRenderer.render(buf, event);
        }

        return buf.toString();
    }

    private void appendEventToBuffer(StringBuilder buf, Converter<ILoggingEvent> c, ILoggingEvent event) {
        buf.append("<td class=\"");
        buf.append(this.computeConverterName(c));
        buf.append("\">");
        String s = c.getClass().equals(LinkConverter.class) ? c.convert(event): Transform.escapeTags(c.convert(event));
        buf.append(s);
        buf.append("</td>");
        buf.append(CoreConstants.LINE_SEPARATOR);
    }

}
