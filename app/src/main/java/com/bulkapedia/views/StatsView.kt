package com.bulkapedia.views

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import com.bulkapedia.R
import com.bulkapedia.database.Database
import com.bulkapedia.database.User
import com.bulkapedia.databinding.AddStatsViewBinding
import com.bulkapedia.labels.Stats

class StatsView(
    context: Context,
    private val user: User,
    private val key: Int
) : Dialog(context) {

    private lateinit var bind: AddStatsViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bind = AddStatsViewBinding.inflate(layoutInflater)
        setContentView(bind.root)

        setCanceledOnTouchOutside(false)
        bind.saveButton.setOnClickListener(this::onClick)
        bind.cancelButton.setOnClickListener(this::onClick)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    private fun onClick(view: View) {
        if (view.id == R.id.save_button) {
            val kills = bind.killsEditText.text.toString().toInt()
            val winrate = bind.winrateEditText.text.toString().toDouble()
            val revives = bind.revivesEditText.text.toString().toInt()

            if (kills > 0 && winrate > 0 && revives > 0) {
                user.mains += key.toString() to Stats(kills, winrate, revives)
                Database().addMainsToUser(user)
            }
        }
        dismiss()
    }

}