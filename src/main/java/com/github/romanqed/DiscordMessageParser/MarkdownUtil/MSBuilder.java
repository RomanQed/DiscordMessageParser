package com.github.romanqed.DiscordMessageParser.MarkdownUtil;

import net.dv8tion.jda.api.utils.MarkdownUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class MSBuilder {
    private final String rawString;
    private final List<TextFormatting> formatting;
    private String syntax;
    private boolean isQuote;
    private boolean isSpoiler;

    public MSBuilder(String rawString, TextFormatting... formatting) {
        this(rawString, "", formatting);
    }

    public MSBuilder(String rawString, String syntax, TextFormatting... formatting) {
        this.rawString = Objects.requireNonNullElse(rawString, "");
        this.formatting = new ArrayList<>(Set.of(formatting));
        setSyntax(syntax);
        isQuote = false;
        isSpoiler = false;
    }

    public MSBuilder setBold() {
        if (!formatting.contains(TextFormatting.BOLD))
            formatting.add(TextFormatting.BOLD);
        return this;
    }

    public MSBuilder setItalic() {
        if (!formatting.contains(TextFormatting.ITALIC))
            formatting.add(TextFormatting.ITALIC);
        return this;
    }

    public MSBuilder setStrikeThrough() {
        if (!formatting.contains(TextFormatting.STRIKETHROUGH))
            formatting.add(TextFormatting.STRIKETHROUGH);
        return this;
    }

    public MSBuilder setUnderlined() {
        if (!formatting.contains(TextFormatting.UNDERLINED))
            formatting.add(TextFormatting.UNDERLINED);
        return this;
    }

    public MSBuilder setSingleLine() {
        formatting.remove(TextFormatting.MULTILINE);
        if (!formatting.contains(TextFormatting.SINGLE_LINE))
            formatting.add(TextFormatting.SINGLE_LINE);
        syntax = "";
        return this;
    }

    public MSBuilder setMultiLine() {
        formatting.remove(TextFormatting.SINGLE_LINE);
        if (!formatting.contains(TextFormatting.MULTILINE))
            formatting.add(TextFormatting.MULTILINE);
        return this;
    }

    public MSBuilder setSyntax(String syntax) {
        if (syntax != null && !rawString.isEmpty() && !syntax.isEmpty()) {
            setMultiLine();
            this.syntax = syntax;
        } else {
            this.syntax = "";
        }
        return this;
    }

    public MSBuilder setSpoiler(boolean spoiler) {
        if (!formatting.contains(TextFormatting.MULTILINE)) {
            this.isSpoiler = spoiler;
        }
        return this;
    }

    public MSBuilder setQuite(boolean quote) {
        this.isQuote = quote;
        return this;
    }

    public String build() {
        StringBuilder resultBuilder = new StringBuilder();
        if (formatting.contains(TextFormatting.MULTILINE)) {
            formatting.remove(TextFormatting.MULTILINE);
            formatting.add(TextFormatting.MULTILINE);
        } else if (formatting.contains(TextFormatting.SINGLE_LINE)) {
            formatting.remove(TextFormatting.SINGLE_LINE);
            formatting.add(TextFormatting.SINGLE_LINE);
        }
        for (TextFormatting textFormatting : formatting) {
            resultBuilder.append(textFormatting.getSeq());
        }
        if (!syntax.isEmpty()) {
            resultBuilder.append(syntax).append("\n");
        }
        resultBuilder.append(rawString);
        for (int i = formatting.size() - 1; i >= 0; --i) {
            resultBuilder.append(formatting.get(i).getSeq());
        }
        String result = resultBuilder.toString();
        if (isSpoiler) {
            result = MarkdownUtil.spoiler(result);
        }
        if (isQuote) {
            if (result.contains("\n"))
                result = MarkdownUtil.quoteBlock(result);
            else
                result = MarkdownUtil.quote(result);
        }
        return result;
    }
}
