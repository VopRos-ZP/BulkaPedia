package com.bulkapedia.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bulkapedia.R
import com.bulkapedia.databinding.TopFragmentBinding
import com.bulkapedia.recycler.TopRecyclerAdapter

class TopFragment : Fragment() {

    lateinit var bind: TopFragmentBinding

    private val args: TopFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = TopFragmentBinding.inflate(inflater, container, false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind.actionBar.actionBar.title = getString(R.string.top_100)
        bind.actionBar.actionBar.setNavigationIcon(R.drawable.backspace)
        bind.actionBar.actionBar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
        bind.recyclerHeroesMaps.layoutManager = LinearLayoutManager(context)
        bind.recyclerHeroesMaps.adapter = TopRecyclerAdapter(args.topModel.asList(), findNavController())
    }

}