package com.github.romanqed.DiscordMessageParser.AnnotationUtil.Annotations.MessageProcessing;

import org.atteo.classindex.IndexAnnotated;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)

@IndexAnnotated
public @interface GuildHandler {
}
