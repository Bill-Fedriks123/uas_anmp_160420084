package com.example.advnative_waroengujang.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.advnative_waroengujang.R
import com.example.advnative_waroengujang.database.LocalPreference
import com.example.advnative_waroengujang.databinding.FragmentAccountBinding
import com.example.advnative_waroengujang.databinding.MenuListItemBinding
import com.example.advnative_waroengujang.viewmodel.AccountViewModel
import com.example.advnative_waroengujang.viewmodel.LoginViewModel

class AccountFragment : Fragment() {

    private lateinit var pref: LocalPreference
    private lateinit var viewModel: AccountViewModel

    private lateinit var binding: FragmentAccountBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        val inflater = LayoutInflater.from(parent.context)
//        val view = MenuListItemBinding.inflate(inflater, parent, false)
        binding = FragmentAccountBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        pref = LocalPreference(requireContext())
        viewModel = ViewModelProvider(this)[AccountViewModel::class.java]
        viewModel.getUserById(pref.getUserId() ?: 1)

        binding.apply {
            btnLogout.setOnClickListener {
                pref.clearSession()
                Toast.makeText(requireContext(), "You're logged out!", Toast.LENGTH_LONG).show()
                val intent = Intent(this@AccountFragment.requireContext(), LoginActivity::class.java)
                startActivity(intent)
                requireActivity().finishAffinity()
            }

            btnUpdatePassword.setOnClickListener {
                val oldPassword = etOldPw.text.toString().trim()
                val newPassword = etNewPw.text.toString().trim()
                val newPasswordConf = etNewPwConf.text.toString().trim()
                if (newPassword != newPasswordConf){
                    Toast.makeText(requireContext(), "New Password Not Match.", Toast.LENGTH_SHORT).show()
                } else {
                    val isUpdated = viewModel.updatePassword(oldPassword, newPassword)
                    if (isUpdated){
                        Toast.makeText(requireContext(), "Password Updated.", Toast.LENGTH_SHORT).show()
                        etOldPw.text.clear()
                        etNewPw.text.clear()
                        etNewPwConf.text.clear()
                    } else {
                        Toast.makeText(requireContext(), "Update Password Failed.", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }


        viewModel.userAccount.observe(viewLifecycleOwner){user ->
            if (user!= null){
                binding.user = user
            }
        }
    }
}