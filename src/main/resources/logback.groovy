import ch.qos.logback.classic.html.LinkConverter

conversionRule("linkEscaper", LinkConverter.class)

appender("htmlLog", FileAppender) {
    file = "/tmp/out.html"
    append = false
    encoder(LayoutWrappingEncoder) {
        layout("ch.qos.logback.classic.html.UnsafeHTMLLayout"){  pattern = "%d{yyyy/MM/dd HH:mm:ss}%-5p%logger{0}%linkEscaper" }
    }
}

root(INFO, ["htmlLog"])