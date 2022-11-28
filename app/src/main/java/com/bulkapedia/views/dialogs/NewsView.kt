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
            .append("из-за проблем, но теперь вы можете смело создавать новые или старые!\n")
            .append("\n")
            .append("А теперь перейдем к нововведением:\n")
            .append("- добавлено диалоговое окно, которое вы видите перед собой\n")
            .append("- добавлена волшебная кнопочка: \"связь с разработчиком\"\n")
            .append("\n")
            .append("Для чего она?\n")
            .append("1. для получения тега мейнера (об этом позже)\n")
            .append("2. для багрепортов (ОЧЕНЬ ВАЖНЫХ)\n")
            .append("Умоляю не спамьте и не злоупотребляйте этим чатом!!!\n")
            .append("\n")
            .append("А теперь о тегах мейнера:\n")
            .append("Это служит показателем для игроков, допустим вы смотрите топ 1 сет на арни,\n")
            .append("вы можете зайти на профиль автора сета и посмотреть: \"ага, а он мейнер арни\"\n")
            .append("так же нажав на данный тег вы можете посмотреть сколько у него на этом персе \n")
            .append("килов, процент побед и сколько он воскресил")
            .toString()
    }

}