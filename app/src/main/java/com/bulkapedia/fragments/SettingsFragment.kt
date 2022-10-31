package com.bulkapedia.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.core.content.edit
import androidx.core.view.allViews
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bulkapedia.MAIN
import com.bulkapedia.R
import com.bulkapedia.databinding.SettingsFragmentBinding
import com.bulkapedia.preference.UserPreferences
import com.bulkapedia.utils.Language
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SettingsFragment : Fragment() {

    private lateinit var bind: SettingsFragmentBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = SettingsFragmentBinding.inflate(inflater, container, false)
        auth = Firebase.auth
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Инициализация ActionBar
        bind.actionBarInclude.actionBar.title = getString(R.string.settings)
        bind.actionBarInclude.actionBar.setNavigationIcon(R.drawable.backspace)
        bind.actionBarInclude.actionBar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
        // Инициализируем default значение
        getRadioButtonByLang().isChecked = true
        // Инициализируем слушатель изменения языка
        bind.langRadioGroup.setOnCheckedChangeListener { _, i ->
            val lang = getLangByButtonId(i)
            MAIN.prefs.setLanguage(lang)
            MAIN.getPreferences().edit {
                putString(UserPreferences.LANGUAGE, MAIN.prefs.getLanguage())
                apply()
            }
            // change language
            Language.update(requireContext(), MAIN.prefs.getLanguage())
            updateView()
        }
        bind.logoutBtn.setOnClickListener {
            val action = SettingsFragmentDirections.actionSettingsFragmentToLoginItem()
            auth.signOut()
            MAIN.prefs.setSigned(false)
            MAIN.getPreferences().edit {
                putBoolean(UserPreferences.SIGNED, MAIN.prefs.getSigned())
                apply()
            }
            findNavController().navigate(action)
        }
    }

    private fun getLangByButtonId(id: Int): String =
        if (id == bind.rbRussian.id) UserPreferences.RU_LANGUAGE
    else UserPreferences.EN_LANGUAGE

    private fun getRadioButtonByLang(): RadioButton =
        if (MAIN.prefs.getLanguage() == UserPreferences.RU_LANGUAGE) bind.rbRussian
        else bind.rbEnglish

    private fun updateView() {
        // Обновляем NavigationView
        MAIN.updateViews()
        // Обновляем данный фрагмент
        bind.root.allViews.iterator().forEach(View::invalidate)
    }

}