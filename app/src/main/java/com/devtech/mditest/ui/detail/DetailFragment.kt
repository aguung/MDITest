package com.devtech.mditest.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.devtech.mditest.data.entity.Product
import com.devtech.mditest.databinding.DetailFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {

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
    }

}