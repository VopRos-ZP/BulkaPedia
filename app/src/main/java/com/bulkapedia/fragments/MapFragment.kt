package com.bulkapedia.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bulkapedia.R
import com.bulkapedia.databinding.MapFragmentBinding

class MapFragment : Fragment() {

    private lateinit var bind: MapFragmentBinding
    private val args: MapFragmentArgs by navArgs()

    private var isScaled = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = MapFragmentBinding.inflate(inflater, container, false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Получаем ViewModel и инициализируем содержимое фрагмента
        args.mapModel.apply {
            // Инициализируем ActionBar
            bind.barInclude.actionBar.title = getString(mapName)
            bind.barInclude.actionBar.setNavigationIcon(R.drawable.backspace)
            bind.barInclude.actionBar.setNavigationOnClickListener {
                findNavController().navigateUp()
            }
            // Инициализируем карту
            bind.mapImageView.setImageResource(map)
            // Инициализируем нажатие на ShowSpawnPoints
            bind.showSpawnPoints.setOnCheckedChangeListener { _, b ->
                val map = if (b) mapSpawns else map
                bind.mapImageView.setImageResource(map)
            }
            // Инициализируем нажатие на карту
            bind.mapImageView.setOnClickListener { v ->
                if (!isScaled) v.animate().scaleX(1.2f).scaleY(1.2f).duration = 500
                if (isScaled) v.animate().scaleX(1f).scaleY(1f).duration = 500
                isScaled = !isScaled
            }
        }
    }
}