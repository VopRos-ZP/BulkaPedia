package com.bulkapedia.views

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import com.bulkapedia.MAIN
import com.bulkapedia.databinding.ShowStatsViewBinding
import com.bulkapedia.labels.Stats

class ShowStatsView (private val stats: Stats) : Dialog(MAIN) {

    private lateinit var bind: ShowStatsViewBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ShowStatsViewBinding.inflate(layoutInflater)
        setContentView(bind.root)
        // init
        bind.killsText.text = "${stats.kills}"
        bind.winrateText.text = "${stats.winrate}%"
        bind.revivesText.text = "${stats.revives}"

        setCanceledOnTouchOutside(true)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

}