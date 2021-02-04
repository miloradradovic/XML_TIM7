package com.project.poverenik.model.util.lists;

import com.project.poverenik.model.util.message.Message;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@XmlSeeAlso({Message.class})
public class MessageList {
    private final List<Message> messageList;

    public MessageList() {
        this.messageList = new ArrayList<>();
    }

    public MessageList(List<Message> messageList) {
        this.messageList = messageList;
    }

    @XmlAnyElement
    public List<Message> getMessageList() {
        return messageList;
    }
}
