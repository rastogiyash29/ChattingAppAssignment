package com.example.chattingapp.adapters

import android.content.Context
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.chattingapp.R
import com.example.chattingapp.models.ChatModel
import com.squareup.picasso.Picasso

class RecyclerViewAdapter(private val context: Context, private val chats: List<ChatModel>) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_chat, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currChatModel = chats[position]
        holder.userNameTV.setText(currChatModel.username)
        holder.emailTV.setText(currChatModel.email)
        Picasso.get().load(currChatModel.imageUrl.substring(0,currChatModel.imageUrl.length)).error(R.drawable.error_icon)
            .resize(150,150).centerCrop().into(holder.profileIV)
    }

    override fun getItemCount(): Int {
        return chats.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val userNameTV=itemView.findViewById<TextView>(R.id.txtUsername)
        val emailTV=itemView.findViewById<TextView>(R.id.txtEmail)
        val profileIV=itemView.findViewById<ImageView>(R.id.imgUser)
    }
}
