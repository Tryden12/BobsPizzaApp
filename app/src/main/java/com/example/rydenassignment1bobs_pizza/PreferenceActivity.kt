package com.example.rydenassignment1bobs_pizza

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CompoundButton
import com.example.rydenassignment1bobs_pizza.databinding.ActivityPreferenceBinding

class PreferenceActivity : AppCompatActivity(),
    CompoundButton.OnCheckedChangeListener {
    private lateinit var binding: ActivityPreferenceBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPreferenceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textPreferenceCheckBox.setOnCheckedChangeListener(this)
        binding.bgPreferenceCheckBox.setOnCheckedChangeListener(this)
    }

    override fun onStart() {
        super.onStart()
        val preferences = getSharedPreferences(
            getString(R.string.preference_storage_name),
            Context.MODE_PRIVATE
        )
        val useLargeText = preferences.getBoolean(getString(R.string.text_size_key), false)
        binding.textPreferenceCheckBox.isChecked = useLargeText

        val useBgAlternate = preferences.getBoolean(getString(R.string.bg_key), false)
        binding.bgPreferenceCheckBox.isChecked = useBgAlternate
    }

    override fun onCheckedChanged(button: CompoundButton?, checked: Boolean) {
        val preferences = getSharedPreferences(
            getString(R.string.preference_storage_name),
            Context.MODE_PRIVATE
        )

        with(preferences.edit()) {
            putBoolean(getString(R.string.text_size_key), checked)
            apply()
        }
        with(preferences.edit()) {
            putBoolean(getString(R.string.bg_key), checked)
            apply()
        }

    }
}