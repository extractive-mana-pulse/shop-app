package com.example.effectivemobiletr.ui.fragments.bottom_navigation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.effectivemobiletr.R
import com.example.effectivemobiletr.databinding.FragmentCatalogBinding
import com.example.effectivemobiletr.model.Items
import com.example.effectivemobiletr.ui.adapters.ItemAdapter
import com.example.effectivemobiletr.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CatalogFragment : Fragment() {

    private val itemAdapter by lazy { ItemAdapter() }
    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding : FragmentCatalogBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentCatalogBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        val selectedText = sharedPref?.getString("selectedText", "")
        binding.sortTv.setText(selectedText)
        val lan = resources.getStringArray(R.array.sort)
        val arrAdapter = ArrayAdapter(requireContext(), R.layout.dromdown_item, lan)
        binding.sortTv.setAdapter(arrAdapter)
    }

    override fun onPause() {
        super.onPause()
        val selectedText = binding.sortTv.text.toString()
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        val editor = sharedPref?.edit()
        editor?.putString("selectedText", selectedText)
        editor?.apply()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val lan = resources.getStringArray(R.array.sort)
        val arrAdapter = ArrayAdapter(requireContext(), R.layout.dromdown_item, lan)

        requireActivity().findViewById<AutoCompleteTextView>(R.id.sort_tv).apply {
            setAdapter(arrAdapter)

            onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ -> (lan[position]) }
        }

        binding.rcView.apply {
            adapter = itemAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
        }

        viewModel.items.observe(viewLifecycleOwner) { itemsList ->
            itemAdapter.setData(itemsList)
        }
        viewModel.fetchItems()
    }

    companion object {
        @JvmStatic
        fun newInstance() = CatalogFragment()
    }
}