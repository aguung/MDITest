package com.devtech.mditest.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.azoft.carousellayoutmanager.CarouselLayoutManager
import com.devtech.mditest.data.entity.Product
import com.devtech.mditest.databinding.DetailFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

import com.azoft.carousellayoutmanager.CarouselZoomPostLayoutListener
import com.azoft.carousellayoutmanager.CenterScrollListener
import com.devtech.mditest.data.entity.Cart
import kotlinx.coroutines.FlowPreview


@AndroidEntryPoint
class DetailFragment : Fragment(), DetailAdapter.OnItemClickListener {

    private val viewModel: DetailViewModel by viewModels()
    private val binding: DetailFragmentBinding by lazy {
        DetailFragmentBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val data: Product? = arguments?.getParcelable("DATA")

        binding.txtProduct.text = data?.name
        binding.txtDescription.text = data?.description
        binding.txtPrice.text = data?.price.toString()
        val detailAdapter = DetailAdapter(this)

        binding.rvDetail.apply {
            adapter = detailAdapter
            layoutManager = CarouselLayoutManager(CarouselLayoutManager.HORIZONTAL, true).apply {
                setPostLayoutListener(CarouselZoomPostLayoutListener())
            }
            setHasFixedSize(true)
            addOnScrollListener(CenterScrollListener())
        }
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.categoryType.value = "1"
            viewModel.productByType.observe(viewLifecycleOwner, { product ->
                detailAdapter.submitList(product.find { it.category.categoryId == 1L }?.product)
            })
        }
        binding.btnCart.setOnClickListener {
            viewModel.addCart(Cart(cartOwnerId = data?.productId!!, quantity = 1))
        }
    }

    override fun onItemClick(product: Product) {

    }

}