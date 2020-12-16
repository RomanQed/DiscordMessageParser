package com.github.romanqed.DiscordMessageParser;

public class DiscordMessageParser {
//    private final CommandList commandList;
//    private final IParseEventHandler eventHandler;
//    private final ConcurrentHashMap<String, ContainerCollection> environmentList;
//    private final EventCollection eventCollection;
//
//    public DiscordMessageParser( CommandList commandList,  IParseEventHandler eventHandler) {
//        this.commandList = Objects.requireNonNullElse(commandList, new CommandList());
//        this.eventHandler = Objects.requireNonNullElse(eventHandler, new DefaultEventHandler());
//        environmentList = new ConcurrentHashMap<>();
//        eventCollection = new EventCollection();
//    }
//
//    public DiscordMessageParser( CommandList commandList) {
//        this(commandList, null);
//    }

//    public void processGuildMessage( GuildMessageReceivedEvent event) {
//        if (event == null || event.getAuthor().isBot()) {
//            return;
//        }
//        GuildReceivedContext context = new GuildReceivedContext(event.getMessage(), eventCollection);
//        StringBuilder prefix = new StringBuilder();
//        if (eventHandler.onGuildMessageParsing(context, prefix)) {
//            return;
//        }
//        ProcessedCommand result = ThreadUtils.parseCommand(event.getMessage().getContentRaw(), prefix.toString());
//        if (result.parseResult == ResultEnum.ERROR) {
//            return;
//        }
//        Command uncheckedCommand = commandList.getCommand(result.commandBody, CommandType.Guild);
//        if (uncheckedCommand.isEmpty()) {
//            eventHandler.onGuildEmptyCommand(context);
//            return;
//        }
//        Guild command = (Guild) uncheckedCommand;
//        if (event.getMember() == null) {
//            return;
//        }
//        if (!ThreadUtils.validateCommandRoles(command.getRoles(), event.getMember().getRoles())) {
//            eventHandler.onGuildRoleError(context);
//            return;
//        }
//        if (!event.getMember().hasPermission(command.getPermissions())) {
//            eventHandler.onGuildPermissionError(context);
//            return;
//        }
//        String guildId = event.getGuild().getId();
//        if (!environmentList.containsKey(guildId)) {
//            environmentList.put(guildId, new ContainerCollection());
//        }
//        ContainerCollection variableList = environmentList.get(guildId);
//        command.execute(new GuildCommandEvent(context, result.rawArguments), variableList);
//    }
//
//    public void processPrivateMessage( PrivateMessageReceivedEvent event) {
//        if (event == null || event.getAuthor().isBot()) {
//            return;
//        }
//        PrivateReceivedContext context = new PrivateReceivedContext(event.getMessage(), eventCollection);
//        StringBuilder prefix = new StringBuilder();
//        if (eventHandler.onPrivateMessageParsing(context, prefix)) {
//            return;
//        }
//        ProcessedCommand result = ThreadUtils.parseCommand(event.getMessage().getContentRaw(), prefix.toString());
//        if (result.parseResult == ResultEnum.ERROR) {
//            return;
//        }
//        Command uncheckedCommand = commandList.getCommand(result.commandBody, CommandType.Private);
//        if (uncheckedCommand.isEmpty()) {
//            eventHandler.onPrivateEmptyCommand(context);
//            return;
//        }
//        Private command = (Private) uncheckedCommand;
//        command.execute(new PrivateCommandEvent(context, result.rawArguments));
//    }
//
//    public void processGuildReaction( GuildMessageReactionAddEvent event) {
//        if (event == null || event.getUser().isBot()) {
//            return;
//        }
//        MessageReaction.ReactionEmote reactionEmote = event.getReactionEmote();
//        if (!reactionEmote.isEmoji()) {
//            return;
//        }
//        String id = event.getChannel().getId() + event.getMessageId() + reactionEmote.getEmoji();
//        eventCollection.execute(id, event.getUser());
//    }
//
//    public void processPrivateReaction( PrivateMessageReactionAddEvent event) {
//        if (event == null || event.getUser() == null || event.getUser().isBot()) {
//            return;
//        }
//        MessageReaction.ReactionEmote reactionEmote = event.getReactionEmote();
//        if (!reactionEmote.isEmoji()) {
//            return;
//        }
//        String id = event.getChannel().getId() + event.getMessageId() + reactionEmote.getEmoji();
//        eventCollection.execute(id, event.getUser());
//    }
//
//    public void processGuildMessageDelete( GuildMessageDeleteEvent event) {
//        if (event == null) {
//            return;
//        }
//        eventCollection.remove(event.getMessageId());
//    }
//
//    public void processPrivateMessageDelete( PrivateMessageDeleteEvent event) {
//        if (event == null) {
//            return;
//        }
//        eventCollection.remove(event.getMessageId());
//    }
}
