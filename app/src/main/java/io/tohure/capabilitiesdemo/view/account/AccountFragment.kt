package io.tohure.capabilitiesdemo.view.account

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import io.tohure.capabilitiesdemo.databinding.FragmentAccountBinding
import io.tohure.capabilitiesdemo.util.ShortcutsUtil

class AccountFragment : Fragment() {

    private var _binding: FragmentAccountBinding? = null
    private val binding get() = _binding!!
    var toastMsg = "Tu sistema no soporta Shortcuts"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        if (_binding == null)
            _binding = FragmentAccountBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnWebsite.setOnClickListener {

            //ShortcutsUtil.setAssistantShortcut(requireContext())

            if (Build.VERSION.SDK_INT >= 25) {
                ShortcutsUtil.setDynamicShortcut(requireContext())
                toastMsg = "Dynamic Shortcut creado!"
            }
            Toast.makeText(context, toastMsg, Toast.LENGTH_SHORT).show()
        }

        binding.btnWebsiteLink.setOnClickListener {
            if (Build.VERSION.SDK_INT >= 28) {
                ShortcutsUtil.setPinnedShortcut(requireContext())
                toastMsg = "Pinned Shortcut creado!"
            }
            Toast.makeText(context, toastMsg, Toast.LENGTH_SHORT).show()
        }
    }

}