package com.bulkapedia.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.bulkapedia.MAIN
import com.bulkapedia.R
import com.bulkapedia.database.Database
import com.bulkapedia.databinding.LoginFragmentBinding
import com.bulkapedia.utils.addUserToShared
import com.bulkapedia.views.OkErrorView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class LoginFragment : Fragment() {

    lateinit var bind: LoginFragmentBinding
    private lateinit var navController: NavController
    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = LoginFragmentBinding.inflate(inflater, container, false)
        auth = Firebase.auth
        database = Firebase.database
        return bind.root
    }

    override fun onStart() {
        super.onStart()

        val cur = auth.currentUser
        if (cur != null) {
            val action = LoginFragmentDirections
                    .actionLoginItemToUserClientFragment(MAIN.prefs.getUser())
            findNavController().navigate(action)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
        bind.actionBarInclude.actionBar.title = getString(R.string.login)
        bind.loginBtn.setOnClickListener {
            val login = bind.loginEt.text.toString()
            val password = bind.passwordEt.text.toString()
            if (!login.contains("@")) {
                val dialog = OkErrorView(MAIN, R.string.error_login_title, R.string.error_email)
                dialog.show()
                return@setOnClickListener
            }
            Database().containsEmail(login) {
                if (!it) {
                    val dialog = OkErrorView(MAIN, R.string.error_login_title, R.string.error_login)
                    dialog.show()
                } else {
                    auth.signInWithEmailAndPassword(login, password).addOnCompleteListener { t ->
                        if (t.isSuccessful) {
                            val res = t.result
                            if (res.user != null) {
                                Database().retrieveUserByEmail(res.user?.email!!) { u ->
                                    if (u.email == login && u.password == password) {
                                        MAIN.prefs.setSigned(true)
                                        MAIN.prefs.setUser(u)
                                        addUserToShared(MAIN.getPreferences(), MAIN.prefs)
                                        val action =
                                            LoginFragmentDirections.actionLoginItemToUserClientFragment(u)
                                        findNavController().navigate(action)
                                    }
                                }
                            }
                        } else {
                            val dialog = OkErrorView(MAIN, R.string.error_login_title, R.string.error_login_password)
                            dialog.show()
                        }
                    }
                }
            }
        }
        bind.regBtn.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginItemToRegistrationFragment()
            findNavController().navigate(action)
        }
        bind.forgotPasswordBtn.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginItemToResetPasswordFragment()
            navController.navigate(action)
        }
    }

}