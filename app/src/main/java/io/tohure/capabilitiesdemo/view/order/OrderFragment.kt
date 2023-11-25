package io.tohure.capabilitiesdemo.view.order

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import io.tohure.capabilitiesdemo.R
import io.tohure.capabilitiesdemo.databinding.FragmentOrderBinding

class OrderFragment : Fragment() {

    private var _binding: FragmentOrderBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        if (_binding == null)
            _binding = FragmentOrderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUI()

        val assistantExtra = requireActivity().intent?.extras

        if (assistantExtra != null) {
            for (extraKey in assistantExtra.keySet()) {
                Log.v("-thr", "Extra: " + extraKey + ": " + assistantExtra.getString(extraKey))
            }
        }

    }

    private fun setUI() {
        context?.let {
            Glide
                .with(it)
                .load("https://fakestoreapi.com/img/61IBBVJvSDL._AC_SY879_.jpg")
                .centerCrop()
                .placeholder(R.drawable.ic_load_product)
                .into(binding.ivProduct)
        }
    }
}