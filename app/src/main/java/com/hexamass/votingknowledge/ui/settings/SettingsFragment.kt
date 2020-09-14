package com.hexamass.votingknowledge.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hexamass.votingknowledge.R
import com.hexamass.votingknowledge.ui.contact.ContactActivity
import com.hexamass.votingknowledge.ui.language.LanguageActivity
import kotlinx.android.synthetic.main.fragment_settings.*

class SettingsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        contact.setOnClickListener { startActivity(ContactActivity.start(requireContext())) }
        setting.setOnClickListener { startActivity(LanguageActivity.start(requireContext())) }
    }

}