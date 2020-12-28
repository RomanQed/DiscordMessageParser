## DiscordMessageParser [ ![Download](https://api.bintray.com/packages/romanqed/maven/DiscordMessageParser/images/download.svg) ](https://bintray.com/romanqed/maven/DiscordMessageParser/_latestVersion)

A simple library for parsing discord activities, which provides functionality for processing commands, emoji reactions,
and various processing actions. Based on JDA.

## Gradle dependency

```Groovy
compile group: 'net.dv8tion', name: 'JDA', version: '4.2.0_224'
compile group: 'com.github.romanqed', name: 'DiscordMessageParser', version: '0.1.12'
compile group: 'org.atteo.classindex', name: 'classindex', version: '3.4'
annotationProcessor group: 'org.atteo.classindex', name: 'classindex', version: '3.4'
```

## Samples

##### Guild command

```Java
@GuildCommandClass
public class MyGuildCommand extends GuildCommand {
    public MyGuildCommand() {
        addRoles("MyRole");
        addPermissions(Permission.MESSAGE_MANAGE);
    }

    @Override
    public void execute(Context context) {
        context.getJDAWrapper().sendMessage(toString());
    }
}
```

##### Private command

```Java
@PrivateCommandClass
public class MyPrivateCommand extends PrivateCommand {
    @Override
    public void execute(Context context) {
        context.getJDAWrapper().sendMessage(toString());
    }
}
```

