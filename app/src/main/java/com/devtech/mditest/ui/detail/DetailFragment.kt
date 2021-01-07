package com.devtech.mditest.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.azoft.carousellayoutmanager.CarouselLayoutManager
import com.devtech.mditest.data.entity.Product
import com.devtech.mditest.databinding.DetailFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

import com.azoft.carousellayoutmanager.CarouselZoomPostLayoutListener
import com.azoft.carousellayoutmanager.CenterScrollListener
import com.devtech.mditest.utils.snackBar


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
            viewModel.productByType.observe(viewLifecycleOwner, {
                println("Data nya : ${it[0].product}")
                detailAdapter.submitList(it[0].product)
            })
        }
    }

    override fun onItemClick(product: Product) {
        binding.root.snackBar("Clicked Product : ${product.name}")
    }

}