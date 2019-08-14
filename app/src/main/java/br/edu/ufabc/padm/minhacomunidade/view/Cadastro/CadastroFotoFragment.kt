package br.edu.ufabc.padm.minhacomunidade.view.Cadastro


import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import br.edu.ufabc.padm.minhacomunidade.App
import br.edu.ufabc.padm.minhacomunidade.R
import br.edu.ufabc.padm.minhacomunidade.databinding.CadastroFotoFragmentBinding
import com.mikhaellopez.circularimageview.CircularImageView


class CadastroFotoFragment : Fragment() {

    lateinit var profilePicture: CircularImageView

    private val LOGTAG = CadastroFotoFragment::class.java.getSimpleName()

    private fun isExternalStorageReadable() = (
            Environment.MEDIA_MOUNTED == Environment.getExternalStorageState() ||
                    Environment.MEDIA_MOUNTED_READ_ONLY == Environment.getExternalStorageState())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        val binding = DataBindingUtil.inflate<CadastroFotoFragmentBinding>(inflater, R.layout.cadastro_foto_fragment, container, false)

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

        binding.salvarBtn.setOnClickListener{
                view: View -> view.findNavController().navigate(CadastroFotoFragmentDirections.action_uploadPhotoFragment_to_cadastroGruposFragment())
            //TODO: passar a imagem como parametro
        }

        return binding.root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 0 && resultCode == AppCompatActivity.RESULT_OK) {
            val photoURI : Uri? = data?.data

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


}
