package com.project.organ_vlasti.model.util.lists;

import com.project.organ_vlasti.model.user.User;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@XmlSeeAlso({User.class})
public class UserList {
    private final List<User> users;

    public UserList() {
        this.users = new ArrayList<>();
    }

    public UserList(List<User> users) {
        this.users = users;
    }

    @XmlAnyElement
    public List<User> getUsers() {
        return users;
    }
}
