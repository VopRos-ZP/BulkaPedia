package com.bulkapedia.utils

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import com.bulkapedia.MAIN
import com.bulkapedia.R
import com.bulkapedia.database.Database
import com.bulkapedia.databinding.DialogDeleteBinding
import com.bulkapedia.heroes.HeroList
import com.bulkapedia.models.HeroModel
import com.bulkapedia.sets.UserSet

class TripleButtonUtils {

    companion object {
        val onClickEdit: (UserSet, (HeroModel) -> Unit) -> Unit = { set, f ->
            if (MAIN.prefs.getSigned()) {
                val hero = HeroList.getHeroByBigImage(set.hero)
                val model = HeroModel(hero, hero.getBigIcon(), hero.getName(), hero.getCounterpicks())
                f.invoke(model)
            }
        }
        val onClickDelete: (MutableList<UserSet>,  UserSet, () -> Unit) -> Unit = { sets, uSet, code ->
            val dialog = Dialog(MAIN)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setContentView(R.layout.dialog_delete)
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.setCanceledOnTouchOutside(false)
            val bind = DialogDeleteBinding.bind(dialog.findViewById(R.id.dialog_delete_constrain))
            bind.confirmBtn.setOnClickListener {
                Database().getSetsNode().document(uSet.setId).delete().addOnSuccessListener {
                    sets.remove(uSet)
                    code.invoke()
                }
                dialog.dismiss()
            }
            bind.cancelBtn.setOnClickListener { dialog.dismiss() }
            dialog.show()
        }
    }

}