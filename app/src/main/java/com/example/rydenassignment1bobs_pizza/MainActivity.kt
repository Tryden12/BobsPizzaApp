package com.example.rydenassignment1bobs_pizza

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.annotation.DrawableRes
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.rydenassignment1bobs_pizza.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(),View.OnClickListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewMenuButton.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }
        binding.orderOnlineButton.setOnClickListener {
            val intent = Intent(this, OrderActivity:: class.java)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        val preferences = getSharedPreferences(
            getString(R.string.preference_storage_name),
            Context.MODE_PRIVATE
        )
        val useLargeText = preferences.getBoolean(getString(R.string.text_size_key), false)
        val useBgAlternate = preferences.getBoolean(getString(R.string.bg_key), false)

        if (useLargeText) {
            binding.callUsTextview.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30.0f)
            binding.phoneNumberTextview.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25.0f)
            binding.streetAddressTextview.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25.0f)
            binding.cityStateTextview.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25.0f)
            binding.zipCodeTextview.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25.0f)
        } else {
            binding.callUsTextview.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20.0f)
            binding.phoneNumberTextview.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18.0f)
            binding.streetAddressTextview.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18.0f)
            binding.cityStateTextview.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18.0f)
            binding.zipCodeTextview.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18.0f)
        }


        if (useBgAlternate) {
            binding.bgMainScreen.setBackgroundColor(Color.DKGRAY)
        } else {
            binding.bgMainScreen.setBackgroundResource(R.drawable.bg_order_screen)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.preferences_menu_item) {
            val intent = Intent(this, PreferenceActivity::class.java)
            startActivity(intent)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onClick(p0: View?) {
        TODO("Not yet implemented")
    }
}

