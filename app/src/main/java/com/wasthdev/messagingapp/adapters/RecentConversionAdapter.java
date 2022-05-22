package com.wasthdev.messagingapp.adapters;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wasthdev.messagingapp.databinding.ItemContainerRecentChatsBinding;
import com.wasthdev.messagingapp.listeners.ConversationListener;
import com.wasthdev.messagingapp.model.ChatMessage;
import com.wasthdev.messagingapp.model.User;

import java.util.List;

public class RecentConversionAdapter extends RecyclerView.Adapter<RecentConversionAdapter.ConversionViewHolder>{


   private final List<ChatMessage> chatMessages;
   private final ConversationListener conversationListener;

    public RecentConversionAdapter(List<ChatMessage> chatMessages, ConversationListener conversationListener) {
        this.chatMessages = chatMessages;
        this.conversationListener = conversationListener;
    }

    @NonNull
    @Override
    public ConversionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ConversionViewHolder(
                ItemContainerRecentChatsBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull  RecentConversionAdapter.ConversionViewHolder holder, int position) {
                holder.setData(chatMessages.get(position));
    }

    @Override
    public int getItemCount() {
        return chatMessages.size();
    }

    class ConversionViewHolder extends RecyclerView.ViewHolder{

    ItemContainerRecentChatsBinding binding;

        public ConversionViewHolder(ItemContainerRecentChatsBinding itemContainerRecentChatsBinding) {
            super(itemContainerRecentChatsBinding.getRoot());
           binding = itemContainerRecentChatsBinding;
        }

        void setData(ChatMessage chatMessage){
            binding.imageProfile.setImageBitmap(getConversionImage(chatMessage.conversionImage));
            binding.textName.setText(chatMessage.conversionName);
            binding.textRecentMessage.setText(chatMessage.message);
            binding.getRoot().setOnClickListener(v -> {
                User user = new User();
                user.id = chatMessage.conversionId;
                user.name = chatMessage.conversionName;
                user.image = chatMessage.conversionImage;
               conversationListener.onConversationClicked(user);
            });
        }
    }


    private Bitmap getConversionImage(String encodedImage){
        byte[] bytes = Base64.decode(encodedImage,Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(bytes,0, bytes.length);
    }
}
