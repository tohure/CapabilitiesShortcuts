package io.tohure.capabilitiesdemo.view.product

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.chip.Chip
import com.google.assistant.appactions.suggestions.client.AppShortcutIntent
import com.google.assistant.appactions.suggestions.client.AppShortcutSuggestion
import com.google.assistant.appactions.suggestions.client.AssistantShortcutSuggestionsClient
import io.tohure.capabilitiesdemo.R
import io.tohure.capabilitiesdemo.databinding.FragmentProductsBinding
import io.tohure.capabilitiesdemo.model.Product
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProductFragment : Fragment() {

    private val productViewModel: ProductViewModel by viewModel()

    private var _binding: FragmentProductsBinding? = null
    private val binding get() = _binding!!

    private lateinit var productAdapter: ProductAdapter
    lateinit var appShortcutIntent : AppShortcutIntent
    lateinit var shortcutsClient : AssistantShortcutSuggestionsClient

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

        val assistantExtra = requireActivity().intent?.extras
        val queryParameter = assistantExtra?.getString("featureParam") ?: ""

        //Log extras
        if (assistantExtra != null) {
            for (extraKey in assistantExtra.keySet()) {
                Log.v("-thr", "Extra: " + extraKey + ": " + assistantExtra.get(extraKey))
            }
        }

        setObservers()
        setUI()

        //Call Products
        if (queryParameter.isEmpty()) {
            productViewModel.getProducts()
        } else {
            productViewModel.getProductsByCategory(queryParameter)
        }
    }

    private fun setUI() {
        productAdapter = ProductAdapter()
        binding.rvProducts.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = productAdapter
        }
    }

    private fun setObservers() {
        with(productViewModel) {
            categoryList.observe(viewLifecycleOwner, renderCategories)
            productList.observe(viewLifecycleOwner, renderProducts)
            getCategories()
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
                    inAppPromo(category)
                }
            }
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun inAppPromo(category: String) {
        shortcutsClient = AssistantShortcutSuggestionsClient.builder()
            .setAgentUid("YOUR_AGENT_ID")
            .setContext(requireContext())
            .setVerifyIntents(true)
            .build()

        appShortcutIntent = AppShortcutIntent.builder()
            .setIntentName("actions.intent.OPEN_APP_FEATURE")
            .setPackageName("io.tohure.capabilitiesdemo")
            .setIntentParamName("feature")
            .setIntentParamValue(category)
            .build()

        shortcutsClient.lookupShortcut(appShortcutIntent)
            .addOnSuccessListener {
                if (it.isShortcutPresent) {
                    // app can remind that the user has a shortcut for this app action
                    Toast.makeText(requireContext(), "Recuerda que puedes acceder a este feature por Google Assistant :)", Toast.LENGTH_SHORT).show()
                } else {
                    // app can suggest to create a shortcut
                    suggestShortcut(category)
                    Toast.makeText(requireContext(), "Shortcut can be suggested", Toast.LENGTH_SHORT).show()
                }
            }
            .addOnFailureListener {
                Toast.makeText(requireContext(), "Shortcut promo Failed", Toast.LENGTH_SHORT).show()
                Log.e("-thr", "Shortcut lookup failed", it)
            }
    }

    private fun suggestShortcut(category: String) {
        val kindProductSpanish = if (category == "jewelery") "de Joyería" else "electrónicos"

        val orderShortcut = AppShortcutSuggestion.builder()
            .setAppShortcutIntent(appShortcutIntent)
            .setCommand("Buscar Productos $kindProductSpanish")
            .build()

        shortcutsClient.createShortcutSuggestionIntent(orderShortcut)
            .addOnSuccessListener {
                activity?.application?.startActivity(it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
                Toast.makeText(requireContext(), "Suggesting Shortcut", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Toast.makeText(requireContext(), "Shortcut suggest Failed", Toast.LENGTH_SHORT).show()
                Log.e("-thr", "Shortcut suggest failed", it)
            }
    }
}