package com.bulkapedia.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bulkapedia.MAIN
import com.bulkapedia.R
import com.bulkapedia.database.User
import com.bulkapedia.databinding.RegistrationFragmentBinding
import com.bulkapedia.utils.addUserToShared
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class RegistrationFragment : Fragment() {

    private lateinit var bind: RegistrationFragmentBinding
    private lateinit var database: FirebaseFirestore

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = RegistrationFragmentBinding.inflate(inflater, container, false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        database = Firebase.firestore

        bind.actionBarInclude.actionBar.title = getString(R.string.registration)
        bind.regBtn.setOnClickListener {
            val login = bind.loginEt.text.toString()
            val password = bind.passwordEt.text.toString()
            val confirmPassword = bind.confirmPasswordEt.text.toString()
            val nickname = bind.nicknameEt.text.toString()

            if (confirmPassword != password) {
                Toast.makeText(context, R.string.invalid_passwords, Toast.LENGTH_LONG)
                    .show()
                return@setOnClickListener
            }
            database.collection("users")
                .get().addOnSuccessListener { result ->
                    for (r in result) {
                        if (r["login"].toString() == login) {
                            Toast.makeText(context, "user are register", Toast.LENGTH_LONG)
                                .show()
                            return@addOnSuccessListener
                        }
                    }
                    val map = mapOf(
                        "login" to login,
                        "password" to password,
                        "nickname" to nickname,
                    )
                    database.collection("users")
                        .add(map).addOnSuccessListener { addResult ->
                            addResult.get().addOnSuccessListener { doc ->
                                val user = doc.toObject(User::class.java)
                                if (user != null) {
                                    val action =
                                        RegistrationFragmentDirections.actionRegistrationFragmentToUserClientFragment(
                                            user
                                        )
                                    findNavController().navigate(action)
                                    MAIN.prefs.setUser(user)
                                    MAIN.prefs.setSigned(true)
                                    addUserToShared(MAIN.getPreferences(), MAIN.prefs)
                                }
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