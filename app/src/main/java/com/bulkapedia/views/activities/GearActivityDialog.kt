package com.bulkapedia.views.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.bulkapedia.R
import com.bulkapedia.databinding.ActivityGearDialogBinding
import com.bulkapedia.data.gears.Gear
import com.bulkapedia.data.gears.GearSet
import com.bulkapedia.data.gears.GearsList
import com.bulkapedia.views.temps.recycler.GearRecyclerAdapter

class GearActivityDialog : AppCompatActivity(),
    GearRecyclerAdapter.RecyclerHolderClick {

    private lateinit var bind: ActivityGearDialogBinding
    private lateinit var gears: List<Gear>

    private var gearType = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityGearDialogBinding.inflate(layoutInflater)
        setContentView(bind.root)

        bind.toolbar.actionBar.setNavigationIcon(R.drawable.backspace)
        bind.toolbar.actionBar.setTitle(R.string.back)
        bind.toolbar.actionBar.setNavigationOnClickListener { onBackPressed() }
        val gearIcons = intent.getIntArrayExtra("gearIcons")
        gearType = intent.getStringExtra("gearType") ?: ""
        val mGears = mutableListOf<Gear>()
        mGears.add(0, Gear(GearSet.NONE, getEmptyIconByGearType(), listOf(), emptyMap()))
        gearIcons?.forEach { icon ->
            val index = GearsList.allGears.map { it.icon }.indexOf(icon)
            if (index != -1) {
                val g = GearsList.allGears[index]
                mGears.add(g)
            }
        }
        gears = mGears
        bind.gearsRecycler.adapter = GearRecyclerAdapter(mGears, this) {
            val buffer = StringBuilder()
            it.forEach { e ->
                val num = e.number
                if (num > 0) buffer.append("+")
                buffer.append(num)
                if (e.percent) buffer.append("%")
                buffer.append(" ${getString(e.description)}\n")
            }
            return@GearRecyclerAdapter buffer.toString()
        }
        bind.gearsRecycler.layoutManager = LinearLayoutManager(this)
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        super.onBackPressed()
        setResult(RESULT_CANCELED)
        finish()
    }

    override fun onClick(position: Int, rank: Int) {
        val intent = Intent().apply {
            val icon = gears[position].icon
            putExtra("gear", icon)
            putExtra("gearType", gearType)
            putExtra("rank", rank)
        }
        setResult(RESULT_OK, intent)
        finish()
    }

    private fun getEmptyIconByGearType(): Int = when (gearType) {
        "head" -> R.drawable.empty_head
        "body" -> R.drawable.empty_body
        "arm" -> R.drawable.empty_arm
        "leg" -> R.drawable.empty_leg
        "decor" -> R.drawable.empty_decor
        "device" -> R.drawable.empty_device
        else -> 0
    }

}