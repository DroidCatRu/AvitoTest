package com.example.avitotest.ui.main

import android.content.res.Configuration
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.example.avitotest.R
import com.example.avitotest.databinding.MainFragmentBinding
import com.example.avitotest.utils.GridItemDecoration

class MainFragment : Fragment() {

  companion object {

    fun newInstance() = MainFragment()
  }

  private lateinit var viewModel: MainViewModel
  private lateinit var manager: GridLayoutManager
  private lateinit var adapter: CardsAdapter
  private lateinit var binding: MainFragmentBinding

  override fun onCreateView(
      inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?
  ): View {

    binding = DataBindingUtil.inflate(
        inflater, R.layout.main_fragment, container, false)

    val orientation = resources.configuration.orientation
    if (orientation == Configuration.ORIENTATION_PORTRAIT) {
      manager = GridLayoutManager(activity, 2)
    } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
      manager = GridLayoutManager(activity, 4)
    }
    binding.cardsView.layoutManager = manager

    val density = context!!.resources.displayMetrics.density
    val gridSpacingPx = 8 * density
    binding.cardsView.addItemDecoration(GridItemDecoration(gridSpacingPx.toInt(), manager.spanCount))

    adapter = CardsAdapter(deleteCardListener)
    binding.cardsView.adapter = adapter

    viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    viewModel.cardsData.observe(this, {
      it?.let {
        adapter.submitList(it)
      }
    })

    return binding.root
  }

  private val deleteCardListener = DeleteButtonListener { id ->
    viewModel.deleteCard(id)
  }

  override fun onConfigurationChanged(newConfig: Configuration) {
    super.onConfigurationChanged(newConfig)
    if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
      manager.spanCount = 2
    } else if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
      manager.spanCount = 4
    }
    binding.cardsView.layoutManager = manager
    val density = context!!.resources.displayMetrics.density
    val gridSpacingPx = 8 * density
    binding.cardsView.removeItemDecorationAt(0)
    binding.cardsView.addItemDecoration(GridItemDecoration(gridSpacingPx.toInt(), manager.spanCount))
  }
}