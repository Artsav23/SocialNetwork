package com.example.socialnetwork

import android.app.ProgressDialog
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.core.view.GravityCompat
import com.example.socialnetwork.databinding.FragmentAccountBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage


class AccountFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var storage: FirebaseStorage
    private lateinit var binding: FragmentAccountBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAccountBinding.inflate(inflater)
        auth = Firebase.auth
        storage = Firebase.storage
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.nameAvatar.text = auth.currentUser?.displayName
        uploadFile()
        navViewListener()
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.open_nav_view, menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.openNavView -> binding.drawerLayout.openDrawer(GravityCompat.END)
        }
        return super.onOptionsItemSelected(item)

    }

    private fun navViewListener() {
        binding.navigationView.setNavigationItemSelectedListener {
        when (it.itemId) {
            R.id.quit -> {
                auth.signOut()
                activity?.finish()
                startActivity(Intent(activity, LogInToAccount::class.java))
                }
            }
            true
        }
    }

    private fun uploadFile() {
        var  pd = ProgressDialog(context)
        pd.setTitle("Wait..")
        pd.show()
        storage.reference.child("avatars/" + auth.currentUser?.uid).getBytes(1024*1024).addOnSuccessListener {
            val  bitmap = BitmapFactory.decodeByteArray(it, 0, it.size)
            binding.avatar.setImageBitmap(bitmap)
            pd.dismiss()
        }
            .addOnFailureListener{
                pd.dismiss()
            }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            AccountFragment()
    }
}