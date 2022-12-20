package com.bulkapedia.views.dialogs

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import com.bulkapedia.BuildConfig
import com.bulkapedia.MAIN
import com.bulkapedia.R
import com.bulkapedia.databinding.NewsLayoutBinding
import com.bulkapedia.utils.updateBoolShared

class NewsView : Dialog(MAIN) {

    private lateinit var bind: NewsLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = NewsLayoutBinding.inflate(layoutInflater)
        setContentView(bind.root)
        bind.apply {
            newsTitle.text = getTitle()
            newsContent.text = getContent()
            closeNewsBtn.setOnClickListener {
                MAIN.prefs.setNews(false)
                updateBoolShared()
                dismiss()
            }
        }
        setCanceledOnTouchOutside(false)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    private fun getTitle(): String {
        return StringBuilder()
            .append(context.getString(R.string.whats_new))
            .append(" ").append(BuildConfig.VERSION_NAME)
            .toString()
    }

    private fun getContent(): String {
        return StringBuilder()
            .append("Вот и очередное обновление, что же нового:\n")
            .append(" - Исправлены проблемы с иконками снаряжения,\n")
            .append("сейчас вы увидите белиберду, исправьте это! Отредактируйте ваши сеты\n")
            .append(" - Исправлен калькулятор эффектов\n")
//            .append("\t - Добавлен новый герой: Тесс\n")
            .append("И знайте я пытаюсь сделать это приложение лучше!\n")
            .toString()
    }

}