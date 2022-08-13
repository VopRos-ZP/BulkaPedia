package com.bulkapedia.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.bulkapedia.MAIN
import com.bulkapedia.R
import com.bulkapedia.database.Database
import com.bulkapedia.databinding.LoginFragmentBinding
import com.bulkapedia.utils.addUserToShared

class LoginFragment : Fragment() {

    lateinit var bind: LoginFragmentBinding
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = LoginFragmentBinding.inflate(inflater, container, false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
        if (MAIN.prefs.getSigned()) {
            val user = MAIN.prefs.getUser()
            val action = LoginFragmentDirections.actionLoginItemToUserClientFragment(user)
            findNavController().navigate(action)
        } else {
            bind.actionBarInclude.actionBar.title = getString(R.string.login)
            bind.loginBtn.setOnClickListener {
                val login = bind.loginEt.text.toString()
                val password = bind.passwordEt.text.toString()
                Database().getUserByLogin(login) { user ->
                    if (user != null) {
                        if (user.password == password) {
                            val action = LoginFragmentDirections.actionLoginItemToUserClientFragment(user)
                            MAIN.prefs.setUser(user)
                            MAIN.prefs.setSigned(true)
                            addUserToShared(MAIN.getPreferences(), MAIN.prefs)
                            findNavController().navigate(action)
                        } else {
                            Toast.makeText(context,
                                R.string.error_login_password,
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    } else {
                        Toast.makeText(context, R.string.error_login, Toast.LENGTH_LONG).show()
                    }
                }
            }
            bind.regBtn.setOnClickListener {
                val action = LoginFragmentDirections.actionLoginItemToRegistrationFragment()
                findNavController().navigate(action)
            }
        }
    }

}