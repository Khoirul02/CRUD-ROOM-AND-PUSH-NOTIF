package com.aplikasi.crud_room

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.ContentValues.TAG
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.aplikasi.crud_room.model.Response
import com.aplikasi.crud_room.rest.RetrofitClient
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.android.synthetic.main.activity_firebase.*
import retrofit2.Call
import retrofit2.Callback

class FirebaseActivity : AppCompatActivity() {
    var CHANNEL_ID = "101"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_firebase)
        createNotificationChannel()
        btn_get_token.setOnClickListener {
            FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.w(TAG, "Fetching FCM registration token failed", task.exception)
                    return@OnCompleteListener
                }
                // Get new FCM registration token
                val token = task.result
                Toast.makeText(baseContext, token, Toast.LENGTH_SHORT).show()
                edt_token.setText(token)
            })
        }
        btn_push.setOnClickListener {
            RetrofitClient.instance.pushNotif(edt_title.text.toString().trim(), edt_body.text.toString().trim(),"eBWAeMEjQXefoy76E71UtN:APA91bHVst2Wmy7gzx8EBr0wpBsGoMt2VdEHed_X6_0wbDRQedUvwx7F7KkNVs3qSdQC-UjYoodDaj7BrY4htiPAcUwKlE9UTiVr23rUob6fHixPdXln6qzDppcTB44GvrROn79YS6TX")
                .enqueue(object : Callback<Response> {
                    override fun onResponse(
                        call: Call<Response>?,
                        response: retrofit2.Response<Response>?
                    ) {
                        if(response!!.isSuccessful){
                            Toast.makeText(this@FirebaseActivity, response.body().message, Toast.LENGTH_LONG).show()
                        }
                    }

                    override fun onFailure(call: Call<Response>?, t: Throwable?) {
                        Toast.makeText(this@FirebaseActivity, "Eror : $t", Toast.LENGTH_LONG).show()
                    }
                })
        }
    }

    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.channel_name)
            val descriptionText = getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}
