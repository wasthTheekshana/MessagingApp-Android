package com.wasthdev.messagingapp.listeners;

import com.wasthdev.messagingapp.model.User;

public interface ConversationListener {
    void onConversationClicked(User user);
}
