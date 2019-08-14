package br.edu.ufabc.padm.minhacomunidade.view.Cadastro


import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.widget.ContentLoadingProgressBar
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import br.edu.ufabc.padm.minhacomunidade.App
import br.edu.ufabc.padm.minhacomunidade.R
import br.edu.ufabc.padm.minhacomunidade.databinding.CadastroFotoFragmentBinding
import br.edu.ufabc.padm.minhacomunidade.services.ProfilePictureUploadService
import br.edu.ufabc.padm.minhacomunidade.services.UploadFirebaseStorageContract
import com.mikhaellopez.circularimageview.CircularImageView


class CadastroFotoFragment : Fragment() {

    lateinit var profilePicture: CircularImageView
    lateinit var progressBar: ContentLoadingProgressBar
    lateinit var salvarButton: Button
    lateinit var photoURI : Uri


    private val LOGTAG = CadastroFotoFragment::class.java.getSimpleName()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {


        val binding = DataBindingUtil.inflate<CadastroFotoFragmentBinding>(inflater, R.layout.cadastro_foto_fragment, container, false)

        progressBar = binding.progressBar
        profilePicture = binding.profilePicture
        profilePicture.setOnClickListener{
            val permission = ContextCompat.checkSelfPermission(this.activity!!,
                Manifest.permission.READ_EXTERNAL_STORAGE)

            if(permission == PackageManager.PERMISSION_GRANTED){
                getImage()
            } else {
                // show  rationale
                if (ActivityCompat.shouldShowRequestPermissionRationale(this.activity!! ,
                        Manifest.permission.READ_EXTERNAL_STORAGE )) {
                    val builder = AlertDialog.Builder(this.activity!!)
                    builder.setMessage(R.string.ask_read_permission)
                            .setTitle(R.string.read_permission_required)

                                builder.setPositiveButton("OK"
                                ) { dialog, id ->
                            Log.i(LOGTAG, "Clicked")
                            makeRequest()
                        }

                        val dialog = builder.create()
                    dialog.show()
                } else {
                    makeRequest()
                }
            }

        }

        binding.pularBtn.setOnClickListener{
            view: View -> view.findNavController().navigate(CadastroFotoFragmentDirections.action_uploadPhotoFragment_to_cadastroGruposFragment())
        }

        salvarButton = binding.salvarBtn

        salvarButton.setOnClickListener{
            progressBar.show()

            //viewToGo = it
            uploadImageToFirebaseStorage()


        }

        return binding.root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 0 && resultCode == AppCompatActivity.RESULT_OK) {
            photoURI= data?.data!!

            profilePicture.setImageURI(photoURI)

            if (photoURI == null)
                Toast.makeText(
                    App.context,
                    getString(R.string.no_photo_returned),
                    Toast.LENGTH_LONG).show()
        }
    }

    private fun getImage(){
        val intent = Intent(Intent.ACTION_GET_CONTENT)

        intent.type = "image/*"
        if (intent.resolveActivity(this.activity!!.packageManager) != null)
            startActivityForResult(intent, 0)
        else
            Toast.makeText(
                this.activity!!,
                getString(R.string.invalid_intent),
                Toast.LENGTH_LONG).show()
    }

    private fun makeRequest(){
        ActivityCompat.requestPermissions(this.activity!!,
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
            101)
    }

    private val uploadImageReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            intent?.takeIf { it.hasExtra(UploadFirebaseStorageContract.UPLOAD_PROFILE_PICTURE_STATUS_EXTRA) }?.apply {
                if(getBooleanExtra(UploadFirebaseStorageContract.UPLOAD_PROFILE_PICTURE_STATUS_EXTRA,false)){
                   salvarButton.findNavController().navigate(CadastroFotoFragmentDirections.action_uploadPhotoFragment_to_cadastroGruposFragment())
                } else{
                    Toast.makeText(this@CadastroFotoFragment.activity,R.string.upload_image_failed, Toast.LENGTH_SHORT).show()
                }
            }
            progressBar.hide()

        }
    }



    private fun uploadImageToFirebaseStorage(){
        App.registerBroadcast(uploadImageReceiver, IntentFilter(UploadFirebaseStorageContract.UPLOAD_PROFILE_PICTURE_STATUS))

        val intent =   Intent(this.activity!!, ProfilePictureUploadService::class.java)
        intent.action = UploadFirebaseStorageContract.UPLOAD_PROFILE_PICTURE
        intent.putExtra(UploadFirebaseStorageContract.UPLOAD_PROFILE_PICTURE_EXTRA, photoURI)
        App.context.startService(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        App.unregisterBroadcast(uploadImageReceiver)

    }
}
