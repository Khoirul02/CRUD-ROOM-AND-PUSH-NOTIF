package com.aplikasi.crud_room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.aplikasi.crud_room.database.NoteDao
import com.aplikasi.crud_room.database.NoteRoomDatabase
import com.aplikasi.crud_room.model.Note
import kotlinx.android.synthetic.main.activity_edit.*

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class EditActivity : AppCompatActivity() {
    val EDIT_NOTE_EXTRA = "edit_note_extra"
    private lateinit var note: Note
    private var isUpdate = false
    private lateinit var database: NoteRoomDatabase
    private lateinit var dao: NoteDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        database = NoteRoomDatabase.getDatabase(applicationContext)
        dao = database.getNoteDao()

        if (intent.getParcelableExtra<Note>(EDIT_NOTE_EXTRA) != null){
            button_delete.visibility = View.VISIBLE
            isUpdate = true
            note = intent.getParcelableExtra(EDIT_NOTE_EXTRA)
            edit_text_title.setText(note.title)
            edit_text_body.setText(note.body)

            edit_text_title.setSelection(note.title.length)

        }

        button_save.setOnClickListener {
            val title = edit_text_title.text.toString()
            val body = edit_text_body.text.toString()

            if (title.isEmpty() && body.isEmpty()){
                Toast.makeText(applicationContext, "Note cannot be empty", Toast.LENGTH_SHORT).show()
            }
            else{
                if (isUpdate){
                    saveNote(Note(id = note.id, title = title, body = body))
                }
                else{
                    saveNote(Note(title = title, body = body))
                }
            }

            finish()
        }

        button_delete.setOnClickListener {
            deleteNote(note)
            finish()
        }
    }
    private fun saveNote(note: Note){

        if (dao.getById(note.id).isEmpty()){

            dao.insert(note)
        }
        else{

            dao.update(note)
        }

        Toast.makeText(applicationContext, "Note saved", Toast.LENGTH_SHORT).show()

    }

    private fun deleteNote(note: Note){
        dao.delete(note)
        Toast.makeText(applicationContext, "Note removed", Toast.LENGTH_SHORT).show()
    }
}
