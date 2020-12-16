package com.github.romanqed.DiscordMessageParser.ContainerUtil;

import java.util.Objects;

public class Container {
    private final String name;
    private Object value;
    private String tag;

    public <T> Container(String name, T value, String tag) {
        this.name = Objects.requireNonNullElse(name, "");
        this.value = value;
        this.tag = Objects.requireNonNullElse(tag, "");
    }

    public <T> Container(String name, T value) {
        this(name, value, "");
    }

    public Container(String name) {
        this(name, null, "");
    }

    public Container() {
        this("", null, "");
    }

    public String getName() {
        return name;
    }

    @SuppressWarnings("unchecked")
    public <T> T getValue() {
        try {
            return (T) value;
        } catch (ClassCastException e) {
            return null;
        }
    }

    public <T> void setValue(T value) {
        try {
            this.value = value;
        } catch (ClassCastException e) {
            this.value = null;
        }
    }

    public String getString() {
        return getValue();
    }

    public Integer getInteger() {
        return getValue();
    }

    public Integer parseInteger() {
        try {
            return Integer.parseInt(getValue());
        } catch (Exception e) {
            return null;
        }
    }

    public Double getDouble() {
        return getValue();
    }

    public Double parseDouble() {
        try {
            return Double.parseDouble(getValue());
        } catch (Exception e) {
            return null;
        }
    }

    public Float getFloat() {
        return getValue();
    }

    public Float parseFloat() {
        try {
            return Float.parseFloat(getValue());
        } catch (Exception e) {
            return null;
        }
    }

    public Boolean getBoolean() {
        return getValue();
    }

    public Boolean parseBoolean() {
        try {
            return Boolean.parseBoolean(getValue());
        } catch (Exception e) {
            return null;
        }
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = Objects.requireNonNullElse(tag, "");
    }

    public boolean isEmpty() {
        return (value == null) && (name.contentEquals(""));
    }
}
