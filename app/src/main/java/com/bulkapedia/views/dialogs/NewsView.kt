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
            .append("Заранее хочу передать свои извенения, все сеты пришлось удалить,\n")
            .append(" думаю вы заметили, что предметы сетов идут каким-то непонятным образом,\n")
            .append(" я как раз работаю над решением этой проблемы, к сожелению это приводит\n")
            .append(" к удалению всех сетов, знайте я пытаюсь сделать это приложение лучше!\n")
            .toString()
    }

}