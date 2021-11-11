package io.tohure.capabilitiesdemo.view.product

import android.os.Bundle
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.chip.Chip
import io.tohure.capabilitiesdemo.R
import io.tohure.capabilitiesdemo.databinding.FragmentProductsBinding
import io.tohure.capabilitiesdemo.model.Product
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProductFragment : Fragment() {

    private val productViewModel: ProductViewModel by viewModel()

    private var _binding: FragmentProductsBinding? = null
    private val binding get() = _binding!!

    private lateinit var productAdapter: ProductAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        if (_binding == null)
            _binding = FragmentProductsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViewModel()
        setUI()
    }

    private fun setUI() {
        productAdapter = ProductAdapter()
        binding.rvProducts.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = productAdapter
        }
    }

    private fun setViewModel() {
        with(productViewModel) {
            getCategories()
            categoryList.observe(viewLifecycleOwner, renderCategories)
            productList.observe(viewLifecycleOwner, renderProducts)
            getProducts()
        }
    }

    private val renderProducts = Observer<List<Product>> {
        binding.piLoader.visibility = View.GONE
        binding.rvProducts.visibility = View.VISIBLE
        productAdapter.submitList(it)
    }

    private val renderCategories = Observer<List<String>> { categories ->
        for (category in categories) addChip(category)
    }

    private fun addChip(category: String) {
        binding.cgCategories.addView(
            Chip(ContextThemeWrapper(context, R.style.ChipChoice)).apply {
                text = category
                setOnClickListener {
                    binding.rvProducts.visibility = View.GONE
                    binding.piLoader.visibility = View.VISIBLE
                    productViewModel.getProductsByCategory(category)
                }
            }
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}