## DiscordMessageParser
A simple library for parsing discord activities, which provides functionality for processing commands, emoji reactions, and various processing actions. Based on JDA.

## Gradle dependency
```Groovy
compile group: 'net.dv8tion', name: 'JDA', version: '4.2.0_209'
compile group: 'com.github.romanqed', name: 'DiscordMessageParser', version: '0.0.1'
annotationProcessor group: 'org.atteo.classindex', name: 'classindex', version: '3.4'
```

## Samples
##### Guild command
```Java
@BotCommand
public class MyGuildCommand extends GuildCommand {
    public MyGuildCommand() {
        super("MyGuildCommand", List.of("TestRole"), Permission.MESSAGE_MANAGE);
    }

    @Override
    public void execute(GuildCommandEvent event, VariableList variableList) {
        event.sendMessage("This is my guild command!");
    }
}
```

##### Private command
```Java
@BotCommand
public class MyPrivateCommand extends PrivateCommand {
    public MyPrivateCommand(){
        super("MyPrivateCommand");
    }

    @Override
    public void execute(PrivateCommandEvent event) {
        event.sendMessage("This is my private command!");
    }
}
```

