package com.bulkapedia.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bulkapedia.MAIN
import com.bulkapedia.R
import com.bulkapedia.database.User
import com.bulkapedia.databinding.RegistrationFragmentBinding
import com.bulkapedia.utils.addUserToShared
import com.bulkapedia.views.dialogs.OkErrorView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class RegistrationFragment : Fragment() {

    private lateinit var bind: RegistrationFragmentBinding
    private lateinit var database: FirebaseDatabase
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = RegistrationFragmentBinding.inflate(inflater, container, false)
        auth = Firebase.auth
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        database = Firebase.database

        bind.actionBarInclude.actionBar.title = getString(R.string.registration)
        bind.regBtn.setOnClickListener {
            val login = bind.loginEt.text.toString()
            val password = bind.passwordEt.text.toString()
            val confirmPassword = bind.confirmPasswordEt.text.toString()
            val nickname = bind.nicknameEt.text.toString()

            if (confirmPassword != password) {
                OkErrorView(MAIN, R.string.error_registration_title, R.string.invalid_passwords)
                    .create()
                return@setOnClickListener
            }

            if (login.isEmpty() || password.isEmpty()
                || confirmPassword.isEmpty()
                || nickname.isEmpty()) {
                val dialog = OkErrorView(MAIN, R.string.error_registration_title, R.string.error_fill_all_fields)
                dialog.show()
            } else {
                auth.createUserWithEmailAndPassword(login, password).addOnCompleteListener {
                    if (it.isSuccessful) {
                        val uid = FirebaseAuth.getInstance().currentUser!!.uid
                        val user = User(login, password, nickname)
                        database.getReference("users")
                            .child(uid)
                            .setValue(user).addOnSuccessListener {
                                val action =
                                    RegistrationFragmentDirections.actionRegistrationFragmentToUserClientFragment(
                                        user
                                    )
                                findNavController().navigate(action)
                                MAIN.prefs.setUser(user)
                                MAIN.prefs.setSigned(true)
                                addUserToShared(MAIN.getPreferences(), MAIN.prefs)
                            }
                    } else {
                        val dialog = OkErrorView(MAIN, R.string.error_registration_title, R.string.error_registration)
                        dialog.show()
                    }
                }
            }
        }
        bind.loginBtn.setOnClickListener {
            val action = RegistrationFragmentDirections.actionRegistrationFragmentToLoginItem()
            findNavController().navigate(action)
        }
    }

}