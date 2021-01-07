package com.devtech.mditest.ui.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.devtech.mditest.data.entity.ProductAndCart
import com.devtech.mditest.databinding.CartFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartFragment : Fragment(),CartAdapter.OnItemClickListener {

    private val viewModel: CartViewModel by viewModels()
    private val binding: CartFragmentBinding by lazy {
        CartFragmentBinding.inflate(layoutInflater)
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

        val cartAdapter = CartAdapter(this)
        binding.rvCart.apply {
            adapter = cartAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.cart.observe(viewLifecycleOwner,{
                println("Data cart : $it")
                cartAdapter.submitList(it)
            })
        }
    }

    override fun onItemClick(productAndCart: ProductAndCart) {

    }
}