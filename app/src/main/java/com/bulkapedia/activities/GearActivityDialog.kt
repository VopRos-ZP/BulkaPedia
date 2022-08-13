package com.bulkapedia.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.bulkapedia.databinding.ActivityGearDialogBinding
import com.bulkapedia.gears.Gear
import com.bulkapedia.gears.GearsList
import com.bulkapedia.recycler.GearRecyclerAdapter

class GearActivityDialog : AppCompatActivity(),
    GearRecyclerAdapter.RecyclerHolderClick {

    private lateinit var bind: ActivityGearDialogBinding
    private lateinit var gears: List<Gear>

    private var gearType = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityGearDialogBinding.inflate(layoutInflater)
        setContentView(bind.root)

        val gearIcons = intent.getIntArrayExtra("gearIcons")
        gearType = intent.getStringExtra("gearType") ?: ""
        val mGears = mutableListOf<Gear>()
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

    override fun onBackPressed() {
        super.onBackPressed()
        setResult(RESULT_CANCELED)
        finish()
    }

    override fun onClick(position: Int) {
        val intent = Intent().apply {
            val icon = gears[position].icon
            putExtra("gear", icon)
            putExtra("gearType", gearType)
        }
        setResult(RESULT_OK, intent)
        finish()
    }

}