package com.aplikasi.crud_room

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aplikasi.crud_room.model.Note

class NoteAdapter(private val listItems: ArrayList<Note>,
                  private val listener: NoteListener) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteAdapter.NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteAdapter.NoteViewHolder, position: Int) {
        val item = listItems[position]
        holder.textViewTitle.text = item.title
        holder.textViewBody.text = item.body
        holder.itemView.setOnClickListener {
            listener.OnItemClicked(item)
        }
    }

    override fun getItemCount(): Int {
        return listItems.size
    }
    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var textViewTitle = itemView.findViewById<TextView>(R.id.text_view_title)
        var textViewBody = itemView.findViewById<TextView>(R.id.text_view_body)
    }
    interface NoteListener{
        fun OnItemClicked(note: Note)
    }
}