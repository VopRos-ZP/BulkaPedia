package com.bulkapedia.views

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import com.bulkapedia.R
import com.bulkapedia.databinding.OneErrorLayoutBinding

class OkErrorView(
    context: Context,
    private val errorTitle: Int,
    private val errorContent: Int
) : Dialog(context, R.style.DialogStyle) {

    private lateinit var bind: OneErrorLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bind = OneErrorLayoutBinding.inflate(layoutInflater)
        setContentView(bind.root)

        bind.errorTitle.setText(errorTitle)
        bind.messageError.setText(errorContent)
        bind.okBtn.setOnClickListener { dismiss() }
        setCanceledOnTouchOutside(false)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

}