package com.example.socialnetwork

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.socialnetwork.databinding.FragmentAddPublictionBinding


class AddPublicationFragment(private val adapter: Adapter) : Fragment() {

   private lateinit var binding: FragmentAddPublictionBinding
   private lateinit var launcher: ActivityResultLauncher <Intent>
   private lateinit var image: Uri

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddPublictionBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnChooseImage.setOnClickListener {
            launchGallery()
        }
        binding.photo.setOnClickListener {
            launchGallery()
        }
        changePhoto()
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_add_public, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.add -> {
                try {
                    val data = PublicationModel(
                        image,
                        "Comments: " + binding.commentEditText.text.toString()
                    )
                    adapter.add(data)
                    binding.photo.setImageResource(R.drawable.ic_add)
                    binding.commentEditText.text = null
                    parentFragmentManager.beginTransaction().replace(R.id.placeHolder, Publication(adapter)).commit()

                }
                catch (e: Exception) {
                    Toast.makeText(context, "Choose Photo", Toast.LENGTH_SHORT).show()
                }
            }

        }

        return super.onOptionsItemSelected(item)
    }

    private fun launchGallery(){
        val  intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        launcher.launch(intent)
    }

    private fun changePhoto() {
        launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            image = Uri.parse(it.data?.data.toString())
            binding.photo.setImageURI(image)
        }
    }
}