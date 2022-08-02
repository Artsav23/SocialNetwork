package com.example.socialnetwork

import android.app.ActionBar
import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.socialnetwork.databinding.FragmentAddPublictionBinding


class AddPublicationFragment(private val adapter: Adapter) : Fragment() {

   private lateinit var binding: FragmentAddPublictionBinding
   private lateinit var launcher: ActivityResultLauncher <Intent>
   private  var image: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddPublictionBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        image = R.drawable.ic_add
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
                val data = PublicationModel(image,
                    "Comments: "+binding.commentEditText.text.toString())
                adapter.add(data)
                binding.photo.setImageResource(R.drawable.ic_add)
                binding.commentEditText.text = null
            }
        }
        parentFragmentManager.beginTransaction().replace(R.id.placeHolder, Publication(adapter)).commit()
        return super.onOptionsItemSelected(item)
    }

    private fun launchGallery(){
        val  intent = Intent( Intent.ACTION_PICK)
        intent.type = "image/*"
        launcher.launch(intent)
    }

    private fun changePhoto() {
        launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            binding.photo.setImageURI(it.data?.data)
            image = binding.photo.toString().toInt()
        }
    }
}