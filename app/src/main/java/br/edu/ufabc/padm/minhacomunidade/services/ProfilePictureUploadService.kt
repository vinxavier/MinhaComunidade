package br.edu.ufabc.padm.minhacomunidade.services

import android.app.IntentService
import android.app.Service
import android.content.Intent
import android.net.Uri
import android.os.IBinder
import android.util.Log
import br.edu.ufabc.padm.minhacomunidade.App
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.net.URI

object UploadFirebaseStorageContract{
    const val UPLOAD_PROFILE_PICTURE = "upload_profile_picture"
    const val UPLOAD_PROFILE_PICTURE_EXTRA = "upload_profile_picture_extra"

    const val UPLOAD_PROFILE_PICTURE_STATUS = "upload_profile_picture_status"
    const val UPLOAD_PROFILE_PICTURE_STATUS_EXTRA = "upload_profile_picture_status_extra"

    const val GET_PROFILE_PICTURE = "get_profile_picture"
    const val GET_PROFILE_PICTURE_EXTRA = "get_profile_picture_extra"

    const val GET_PROFILE_PICTURE_STATUS = "get_profile_picture_status"
    const val GET_PROFILE_PICTURE_STATUS_EXTRA = "get_profile_picture_status_extra"


}

class ProfilePictureUploadService : IntentService("ProfilePictureUploadService") {

    lateinit var profileImageReference: StorageReference
    private lateinit var auth: FirebaseAuth

    private val TAG = "PictureUploadService"

    override fun onHandleIntent(intent: Intent?) {
        when (intent?.action) {
            UploadFirebaseStorageContract.UPLOAD_PROFILE_PICTURE -> {
                if(intent.hasExtra(UploadFirebaseStorageContract.UPLOAD_PROFILE_PICTURE_EXTRA)){
                    val uriProfileImage = intent.getParcelableExtra<Uri>(UploadFirebaseStorageContract.UPLOAD_PROFILE_PICTURE_EXTRA)
                    profileImageReference = FirebaseStorage.getInstance().getReference("profilepics/"+auth.currentUser!!.uid+"profilepic.jpg")
                    profileImageReference.putFile(uriProfileImage).addOnCompleteListener {
                        sendStatus(it.isSuccessful)
                    }
                }
            }

        }
    }

    override fun onCreate() {
        super.onCreate()
        auth = FirebaseAuth.getInstance()
        Log.i(TAG, "Service onCreate")


    }

    override fun onBind(intent: Intent): IBinder? {
        Log.i(TAG, "Service onBind")
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "Service onDestroy")
    }

    private fun sendStatus(status: Boolean){
        Intent(UploadFirebaseStorageContract.UPLOAD_PROFILE_PICTURE_STATUS).apply {
            putExtra(UploadFirebaseStorageContract.UPLOAD_PROFILE_PICTURE_STATUS_EXTRA,
                status
            )
            App.sendBroadcast(this)
        }

    }
}
