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
import com.bulkapedia.database.Database
import com.bulkapedia.database.User
import com.bulkapedia.databinding.ResetPasswordFragmentBinding
import com.bulkapedia.labels.Stats
import com.bulkapedia.utils.addUserToShared
import com.bulkapedia.views.OkErrorView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class ResetPasswordFragment : Fragment() {

    private lateinit var bind: ResetPasswordFragmentBinding

    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = ResetPasswordFragmentBinding.inflate(inflater, container, false)
        auth = Firebase.auth
        database = Firebase.database
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bind.actionBar.actionBar.setTitle(R.string.reset_password)
        bind.actionBar.actionBar.setNavigationIcon(R.drawable.backspace)
        bind.actionBar.actionBar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        bind.sendResetCodeBtn.setOnClickListener {
            // get email
            val email = bind.loginEt.text.toString()
            if (email.isEmpty()) {
                val dialog = OkErrorView(MAIN, R.string.error_forgot_pass_title, R.string.enter_email)
                dialog.show()
            } else
            // check on verify email
                Database().getUsersNode().addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val emails = mutableListOf<String>()
                    for (sn in snapshot.children) {
                        sn.children.forEach { data ->
                            if (data.key!! == "email")
                                emails.add(data.value as String)
                        }
                    }
                    if (!emails.contains(email)) {
                        val dialog = OkErrorView(MAIN, R.string.error_forgot_pass_title, R.string.error_login)
                        dialog.show()
                    } else {
                        // send reset code
                        auth.sendPasswordResetEmail(email)
                        Toast.makeText(MAIN, getString(R.string.email_send), Toast.LENGTH_LONG).show()
                        bind.sendResetCodeBtn.setText(R.string.save)
                        bind.sendResetCodeBtn.setOnClickListener {
                            auth.signInWithEmailAndPassword(email, bind.passwordEt.text.toString()).addOnCompleteListener {
                                if (it.isSuccessful) {
                                    Database().getUsersNode().addListenerForSingleValueEvent(object : ValueEventListener {
                                        override fun onDataChange(snapshot: DataSnapshot) {
                                            var doc = ""
                                            var user: User? = null
                                            for (sn in snapshot.children) {
                                                val strings = mutableMapOf<String, String>()
                                                val mains = mutableMapOf<String, Stats>()
                                                sn.children.forEach { data ->
                                                    if (data.key!! == "mains"){
                                                        data.children.forEach { main ->
                                                            val statsMap = mutableMapOf<String, Double>()
                                                            main.children.forEach { mainData ->
                                                                statsMap += mainData.key!! to mainData.value as Double
                                                            }
                                                            mains += main.key!! to
                                                                    Stats(statsMap["kills"]!!.toInt(),
                                                                        statsMap["winrate"]!!,
                                                                        statsMap["revives"]!!.toInt()
                                                                    )
                                                        }
                                                    } else
                                                        strings += data.key!! to (data.value as String)
                                                }
                                                if (strings["email"] == email) {
                                                    doc = sn.key!!
                                                    user = User(
                                                        strings["email"],
                                                        bind.passwordEt.text.toString(),
                                                        strings["nickname"], mains
                                                    )
                                                    break
                                                }
                                            }
                                            if (user != null) {
                                                Database().getUsersNode().child(doc).setValue(user)
                                            }
                                        }

                                        override fun onCancelled(error: DatabaseError) {}
                                    })
                                    Database().retrieveUserByEmail(email) { user ->
                                        MAIN.prefs.setUser(user)
                                        MAIN.prefs.setSigned(true)
                                        addUserToShared(MAIN.getPreferences(), MAIN.prefs)
                                        val action = ResetPasswordFragmentDirections
                                            .actionResetPasswordFragmentToUserClientFragment(user)
                                        findNavController().navigate(action)
                                    }
                                } else {
                                    val dialog = OkErrorView(MAIN, R.string.error_forgot_pass_title, R.string.invalid_passwords)
                                    dialog.show()
                                }
                            }
                        }
                    }
                }
                override fun onCancelled(error: DatabaseError) {}
            })
        }
    }

}