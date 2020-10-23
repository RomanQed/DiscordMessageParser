package com.github.romanqed.DiscordMessageParser.MarkdownUtil;

public enum TextFormatting {
    BOLD("**"),
    ITALIC("*"),
    STRIKETHROUGH("~~"),
    UNDERLINED("__"),
    SINGLE_LINE("`"),
    MULTILINE("```");
    private final String seq;

    TextFormatting(String seq) {
        this.seq = seq;
    }

    public String getSeq() {
        return seq;
    }
}
