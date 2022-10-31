package com.bulkapedia.recycler

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.bulkapedia.MAIN
import com.bulkapedia.R
import com.bulkapedia.database.Database
import com.bulkapedia.databinding.DialogSetBinding
import com.bulkapedia.databinding.TopSetItemBinding
import com.bulkapedia.fragments.TopFragmentDirections
import com.bulkapedia.gears.Gear
import com.bulkapedia.gears.GearsList
import com.bulkapedia.models.TopModel
import com.bulkapedia.sets.GearCell

class TopRecyclerAdapter (
    private val topSto: List<TopModel>,
    private val navController: NavController
) : RecyclerView.Adapter<TopRecyclerAdapter.TopRecyclerHolder>() {

    class TopRecyclerHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = TopSetItemBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopRecyclerHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.top_set_item, parent, false)
        return TopRecyclerHolder(view)
    }

    override fun onBindViewHolder(holder: TopRecyclerHolder, position: Int) {
        val model = topSto[position]
        holder.binding.apply {
            topNumber.text = model.number.toString()
            Database().retrieveUserByNickname(model.nickname) { user ->
                topName.text = user.nickname
                if (MAIN.prefs.getUser().nickname != user.nickname) {
                    topName.setOnClickListener {
                        val action = TopFragmentDirections.actionTopFragmentToUserClientFragment(user, true)
                        navController.navigate(action)
                    }
                }
            }
            topLikes.text = model.set.likes.toString()
            commentView.setOnClickListener {
                val action = TopFragmentDirections.actionTopFragmentToCommentsFragment(model.set)
                navController.navigate(action)
            }
            book.setOnClickListener {
                val dialog = Dialog(MAIN)
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
                dialog.setContentView(R.layout.dialog_set)
                dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

                val bind = DialogSetBinding.bind(dialog.findViewById(R.id.dialog_constrain))
                bind.setInclude.apply {
                    val ivGears = listOf(
                        ivHead, ivBody,
                        ivArm, ivLeg,
                        ivDecor, ivDevice
                    )
                    val gears = getGears(model.set.gears)
                    val cells = listOf(
                        GearCell.HEAD, GearCell.BODY,
                        GearCell.ARM, GearCell.LEG,
                        GearCell.DECOR, GearCell.DEVICE,
                    )
                    cells.forEachIndexed { i, cell ->
                        if (gears[cell] != null) {
                            ivGears[i].setImageResource(gears[cell]!!.icon)
                        }
                    }
                }
                bind.close.setOnClickListener {
                    dialog.dismiss()
                }
                dialog.show()
            }
        }
    }

    private fun getGears(gears: Map<GearCell, Int>): Map<GearCell, Gear?> {
        return gears.map { gs ->
            val index = GearsList.allGears.map{ it.icon }.indexOf(gs.value)
            if (index == -1)
                gs.key to null
            else
                gs.key to GearsList.allGears[index]
        }.toMap()
    }

    override fun getItemCount(): Int {
        return topSto.size
    }


}