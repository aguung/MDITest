package com.devtech.mditest.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.devtech.mditest.R
import com.devtech.mditest.data.entity.Category
import com.devtech.mditest.data.entity.Product
import com.devtech.mditest.databinding.HomeFragmentBinding
import com.devtech.mditest.ui.MainActivity
import com.devtech.mditest.utils.snackBar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map

@AndroidEntryPoint
class HomeFragment : Fragment(), CategoryAdapter.OnItemClickListener, ProductAdapter.OnItemClickListener {

    private val viewModel: HomeViewModel by viewModels()
    private val binding: HomeFragmentBinding by lazy {
        HomeFragmentBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val categoryAdapter = CategoryAdapter(this)
        val productAdapter = ProductAdapter(this)
        binding.rvCategory.apply {
            adapter = categoryAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
        }
        binding.rvProduct.apply {
            adapter = productAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.category.observe(viewLifecycleOwner, {
                categoryAdapter.submitList(it)
            })
            viewModel.categoryType.value = "1"
            viewModel.productByType.observe(viewLifecycleOwner, {  product ->
                productAdapter.submitList(product.find { it.category.categoryId == 1L }?.product)
            })
            viewModel.countCart.observe(viewLifecycleOwner,{
                if(it > 0){
                    val badge = (activity as MainActivity).navigationView.getOrCreateBadge(R.id.navigation_cart)
                    badge.number = it
                }
            })
        }
    }

    override fun onItemClick(category: Category) {
        binding.root.snackBar("Clicked Category : ${category.name}")
        viewModel.categoryType.value = category.categoryId.toString()
    }

    override fun onItemClick(product: Product) {
        binding.root.snackBar("Clicked Product : ${product.name}")
        val data = Bundle().apply {
            putParcelable("DATA", product)
        }
        findNavController().navigate(R.id.action_navigation_home_to_detailFragment, data)
    }
}