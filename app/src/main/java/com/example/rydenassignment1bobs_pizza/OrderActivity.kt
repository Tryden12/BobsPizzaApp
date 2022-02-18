package com.example.rydenassignment1bobs_pizza

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AlertDialog
import com.example.rydenassignment1bobs_pizza.databinding.ActivityOrderBinding
import java.io.PrintWriter


class OrderActivity : AppCompatActivity(), View.OnClickListener,
RadioGroup.OnCheckedChangeListener{

    private lateinit var binding: ActivityOrderBinding

    // Order total variables & tax
    private var subtotal = 0.00
    private var finalTotal = 0.00
    private var tax = .1

    // Pizza, drink, delivery price
    private var pizzaPrice = 15.00
    private var drinkPrice = 1.50
    private var deliveryPrice = 3.00
    private var toppingPrice = 2.00

    // Count variables
    var cokeCount = 0
    var pepsiCount = 0
    var waterCount = 0
    var pizzaCount = 0

    // Pizza style
    private var pizzaStyle = ""

    // Create mutable list to write to fill
    private val orderList = mutableListOf<String>()





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set the counts for number of pizzas and drinks to zero
        binding.cokeCountEditTextNumber.setText("0")
        binding.pepsiCountEditTextNumber.setText("0")
        binding.waterCountEditTextNumber.setText("0")
        binding.pizzaCountEditTextNumber.setText("0")

        // Created radio group listener
        // I learned when adding radioButtons - they must be added directly to RadioGroup
        // I used LinearLayout to make the radio buttons horizontal
        // However, this caused the deselcting manner not to work
        // Fixed it by implementing a java class and making this group:
        RadioGroupCheckListener.makeGroup(
            binding.pepperoniRadioButton,
            binding.napolitanaRadioButton,
            binding.frontenacRadioButton, binding.classicCheeseRadioButton
        )

        // Button and Checkbox listeners
        binding.minusCokeImageButton.setOnClickListener(this)
        binding.addCokeImageButton.setOnClickListener(this)
        binding.minusPepsiImageButton.setOnClickListener(this)
        binding.addPepsiImageButton.setOnClickListener(this)
        binding.minusWaterImageButton.setOnClickListener(this)
        binding.addWaterImageButton.setOnClickListener(this)
        binding.minusPizzaCountImageButton.setOnClickListener(this)
        binding.addPizzaCountImageButton.setOnClickListener(this)
        binding.pepperoniCheckBox.setOnClickListener(this)
        binding.cheeseCheckBox.setOnClickListener(this)
        binding.sausageCheckBox.setOnClickListener(this)
        binding.mushroomCheckBox.setOnClickListener(this)
        binding.greenPepperCheckBox.setOnClickListener(this)
        binding.anchovyCheckBox.setOnClickListener(this)

        // Places orders and writes to internal file
        binding.placeOrderButton.setOnClickListener(this)


        // Radio groups
        binding.crustStyleRadioGroup.setOnCheckedChangeListener(this)
        binding.pickupDeliveryRadioGroup.setOnCheckedChangeListener(this)
        binding.tipRadioGroup.setOnCheckedChangeListener(this)
        binding.pizzaStyleRadioGroup.setOnCheckedChangeListener(this)

    }




    override fun onClick(v: View?) {
        var toppingCount = 0

    // Debug final total
        Log.i("DEBUGGING", "START OF FUNCTION -- count: ${toppingCount} and final total:${finalTotal}")

        // Declare array of checkboxes
        var checkboxes: ArrayList<CheckBox> = arrayListOf()

        // Make array list of toppings
        checkboxes = arrayListOf(
            binding.pepperoniCheckBox,
            binding.cheeseCheckBox,
            binding.sausageCheckBox,
            binding.mushroomCheckBox,
            binding.greenPepperCheckBox,
            binding.anchovyCheckBox
        )
        // Loop through the toppings checkboxes
        for (checkbox in checkboxes) {
            if (checkbox.isChecked) {
                toppingCount +=1
                finalTotal += 2.00
                binding.toppingsTotalTextView.setText("Topping \$2.00 each x ${toppingCount}")
                binding.finalTotalTextview.setText("$${String.format("%.2f", finalTotal)}")
                break;
            }

            /*
            else {
                toppingCount -= 1
                finalTotal -= 2.00
                binding.toppingsTotalTextView.setText("Topping \$2.00 each x ${toppingCount}")
                binding.finalTotalTextview.setText("$${String.format("%.2f", finalTotal)}")
                break;
            }

             */
        }

        Log.i("DEBUGGING", "count: ${toppingCount} and final total:${finalTotal}")

        // Coke counting
        if (v?.getId() == R.id.minus_coke_imageButton) {
            if (cokeCount > 0) {
                cokeCount -= 1
                finalTotal -= drinkPrice
                binding.cokeCountEditTextNumber.setText("${cokeCount}")
                binding.cokeTotalTextView.setText("Coke \$1.50 x ${cokeCount}")
                binding.finalTotalTextview.setText("$${String.format("%.2f", finalTotal)}")


            }
        } else if (v?.getId() == R.id.add_coke_imageButton) {
            cokeCount += 1
            finalTotal += drinkPrice
            binding.cokeCountEditTextNumber.setText("${cokeCount}")
            binding.cokeTotalTextView.setText("Coke \$1.50 x ${cokeCount}")
            binding.finalTotalTextview.setText("$${String.format("%.2f", finalTotal)}")
        }

        Log.i("DEBUGGING", "count: ${toppingCount} and final total:${finalTotal}")

        // Pepsi counting
        if (v?.getId() == R.id.minus_pepsi_imageButton) {
            if (pepsiCount > 0) {
                pepsiCount -= 1
                finalTotal -= drinkPrice
                binding.pepsiCountEditTextNumber.setText("${pepsiCount}")
                binding.pepsiTotalTextView.setText("Pepsi \$1.50 x ${pepsiCount}")
                binding.finalTotalTextview.setText("$${String.format("%.2f", finalTotal)}")
            }
        } else if (v?.getId() == R.id.add_pepsi_imageButton) {
            pepsiCount += 1
            finalTotal += drinkPrice
            binding.pepsiCountEditTextNumber.setText("${pepsiCount}")
            binding.pepsiTotalTextView.setText("Pepsi \$1.50 x ${pepsiCount}")
            binding.finalTotalTextview.setText("$${String.format("%.2f", finalTotal)}")
        }

        Log.i("DEBUGGING", "count: ${toppingCount} and final total:${finalTotal}")

        // Water counting
        if (v?.getId() == R.id.minus_water_imageButton) {
            if (waterCount > 0) {
                waterCount -= 1
                finalTotal -= drinkPrice
                binding.waterCountEditTextNumber.setText("${waterCount}")
                binding.waterTotalTextView.setText("Water \$1.50 x ${waterCount}")
                binding.finalTotalTextview.setText("$${String.format("%.2f", finalTotal)}")
            }
        } else if (v?.getId() == R.id.add_water_imageButton) {
            waterCount += 1
            finalTotal += drinkPrice
            binding.waterCountEditTextNumber.setText("${waterCount}")
            binding.waterTotalTextView.setText("Water \$1.50 x ${waterCount}")
            binding.finalTotalTextview.setText("$${String.format("%.2f", finalTotal)}")
        }

        Log.i("DEBUGGING", "count: ${toppingCount} and final total:${finalTotal}")

        // Pizza counting
        if (v?.getId() == R.id.minus_pizza_count_imageButton) {
            if (pizzaCount > 0) {
                pizzaCount -= 1
                finalTotal -= pizzaPrice
                binding.pizzaCountEditTextNumber.setText("${pizzaCount}")
                binding.pizzaTotalTextView.setText("${pizzaStyle} Pizza \$15.00 x ${pizzaCount}")
                binding.finalTotalTextview.setText("$${String.format("%.2f", finalTotal)}")
            }
        } else if (v?.getId() == R.id.add_pizza_count_imageButton) {
            pizzaCount += 1
            finalTotal += pizzaPrice
            binding.pizzaCountEditTextNumber.setText("${pizzaCount}")
            binding.pizzaTotalTextView.setText("Pizza \$15.00 x ${pizzaCount}")
            binding.finalTotalTextview.setText("$${String.format("%.2f", finalTotal)}")
        }


        Log.i("DEBUGGING", "count: ${toppingCount} and final total:${finalTotal}")

        // Calculate tip
        val tip10   = finalTotal * .1
        val tip15   = finalTotal * .15
        val tip20   = finalTotal * .20


        Log.i("DEBUGGING", "count: ${toppingCount} and final total:${finalTotal}")

        // Place order
        if (v?.getId() == R.id.place_order_button) {
            // Create alert dialog box for confirmation
            val builder = AlertDialog.Builder(binding.root.context)
            // Create new intent to redirect if user presses ok
            val intent = Intent(this, MainActivity::class.java)
            // Listener for dialog box
            val listener = object : DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface?, which: Int) {

                    // Set listener for adding orders
                    binding.placeOrderButton.setOnClickListener{ addOrders() }
                    if (which == DialogInterface.BUTTON_POSITIVE) {
                        startActivity(intent)
                        addOrders()
                    }
                }
            }

            // Format tax, tip
            String.format("%.2f", tax)
            String.format("%.2f", tip10)
            // dialog box builder
            builder
                .setTitle(R.string.order_confirmation)
                .setMessage(
                            "Pizza \$15.00 x ${pizzaCount}\n" +
                            "Topping \$2.00 each x ${toppingCount}\n" +
                            "Coke \$1.50 x ${cokeCount}\n" +
                            "Pepsi \$1.50 x ${pepsiCount}\n" +
                            "Water \$1.50 x ${waterCount}\n" +
                            "Tax 10%: $${String.format("%.2f", tax)}\n" +
                            "Tip 10%: $${String.format("%.2f", tip10)}\n\n" +
                            "Final Total:" +
                            "$${String.format("%.2f", finalTotal)}"
                )
                .setPositiveButton(android.R.string.ok, listener)
                .setIcon(R.drawable.icon_done)
                .show()

        }




        Log.i("DEBUGGING", "END OF FUNCTION -- count: ${toppingCount} and final total:${finalTotal}")

    }






    override fun onCheckedChanged(radioGroup: RadioGroup?, buttonId: Int) {

        // Calculate tax
        tax         =   finalTotal * tax
        finalTotal  +=  tax

        // Calculate tip
        val tip10   = finalTotal * .1
        val tip15   = finalTotal * .15
        val tip20   = finalTotal * .20

        // Check which pizza style radio button is checked
        var idPizzaStyle = binding.pizzaStyleRadioGroup.getCheckedRadioButtonId();
        when (idPizzaStyle) {
            R.id.pepperoni_radioButton ->      { pizzaStyle = R.string.pepperoni_pizza.toString() }
            R.id.frontenac_radioButton ->      { pizzaStyle = R.string.frontenac_pizza.toString() }
            R.id.napolitana_radioButton ->     { pizzaStyle = R.string.napolitana_pizza.toString() }
            R.id.classic_cheese_radioButton -> { pizzaStyle = R.string.classic_cheese_pizza.toString() }
        }



        // Check which tip radio button is checked
        var id = binding.tipRadioGroup.getCheckedRadioButtonId()
        when (id){
            R.id.tip_10_radioButton -> {
                finalTotal += tip10
                binding.tipTotalsTextView.setText("Tip 10%")
                binding.finalTotalTextview.setText("$${String.format("%.2f", finalTotal)}")
            }
            R.id.tip_15_radioButton -> {
                finalTotal += tip15
                binding.tipTotalsTextView.setText("Tip 15%")
                binding.finalTotalTextview.setText("$${String.format("%.2f", finalTotal)}")
            }
            R.id.tip_20_radioButton -> {
                finalTotal += tip20
                binding.tipTotalsTextView.setText("Tip 20%")
                binding.finalTotalTextview.setText("$${String.format("%.2f", finalTotal)}")
            }
        }




        // Check if pickup or delivery radio button is checked
        var id2 = binding.pickupDeliveryRadioGroup.getCheckedRadioButtonId()
        when (id2){
            R.id.pickup_radioButton -> {
                binding.pickupNoChargeTextView.setText("Pickup (no charge)")
            }
            R.id.delivery_radioButton -> {
                finalTotal += deliveryPrice
                binding.tipTotalsTextView.setText("Delivery $3.00")
                binding.finalTotalTextview.setText("$${String.format("%.2f", finalTotal)}")
            }

        }


    }

    fun calculateTotal() {

    }

    // pass in info as argument

    private fun addOrders() {
        val test =  "Order #1:\n"
        /*
                    "Pizza \$15.00 x ${pizzaCount}\n" +
                   // "Topping \$2.00 each x ${count5}\n" +
                    "Coke \$1.50 x ${cokeCount}\n" +
                    "Pepsi \$1.50 x ${pepsiCount}\n" +
                    "Water \$1.50 x ${waterCount}\n" +
                    "Tax 10%: $${tax}\n" +
                    
         */
                    "Tip 10%\n" +
                    "Final Total:\n" +
                    "$${String.format("%.2f", finalTotal)}\n\n"
        orderList.add(test)

        writeData()
    }

    private fun writeData() {
        val bw = openFileOutput("prototypePizzaOrder.txt", MODE_PRIVATE).bufferedWriter()
        val pw = PrintWriter(bw)
        for(order in orderList) {
            pw.println(order)
        }
        pw.close()
    }

    override fun onStart() {
        super.onStart()
        val preferences = getSharedPreferences(
            getString(R.string.preference_storage_name),
            Context.MODE_PRIVATE
        )
        val useLargeText = preferences.getBoolean(getString(R.string.text_size_key), false)

        if (useLargeText) {
            // Textviews
            binding.makeChoicesTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30.0f)
            binding.pizzaStyleTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25.0f)
            binding.crustStyleTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25.0f)
            binding.toppingsTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25.0f)
            binding.numberPizzasTextview.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25.0f)
            binding.drinksTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25.0f)
            binding.tipTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25.0f)
            binding.pickupDeliveryTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25.0f)
            binding.totalsTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25.0f)
            binding.pizzaTotalTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20.0f)
            binding.toppingsTotalTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20.0f)
            binding.cokeTotalTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20.0f)
            binding.pepsiTotalTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20.0f)
            binding.waterTotalTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20.0f)
            binding.taxTotalTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20.0f)
            binding.pickupNoChargeTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20.0f)
            binding.tipTotalsTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20.0f)
            binding.finalTotalTextview.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25.0f)
            binding.cokeTextview.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18.0f)
            binding.pepsiTextview.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18.0f)
            binding.waterTextview.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18.0f)

            // RadioButtons
            binding.pepperoniRadioButton.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18.0f)
            binding.frontenacRadioButton.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18.0f)
            binding.napolitanaRadioButton.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18.0f)
            binding.classicCheeseRadioButton.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18.0f)
            binding.handTossedRadioButton.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18.0f)
            binding.thinCrustRadioButton.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18.0f)
            binding.tip10RadioButton.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18.0f)
            binding.tip15RadioButton.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18.0f)
            binding.tip20RadioButton.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18.0f)
            binding.pickupRadioButton.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18.0f)
            binding.deliveryRadioButton.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18.0f)

            // Checkboxes
            binding.pepperoniCheckBox.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18.0f)
            binding.cheeseCheckBox.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18.0f)
            binding.sausageCheckBox.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18.0f)
            binding.mushroomCheckBox.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18.0f)
            binding.greenPepperCheckBox.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18.0f)
            binding.anchovyCheckBox.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18.0f)
        } else {
            // Textviews
            binding.pizzaStyleTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20.0f)
            binding.crustStyleTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20.0f)
            binding.toppingsTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20.0f)
            binding.numberPizzasTextview.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20.0f)
            binding.drinksTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20.0f)
            binding.tipTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20.0f)
            binding.pickupDeliveryTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20.0f)
            binding.totalsTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20.0f)
            binding.pizzaTotalTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14.0f)
            binding.toppingsTotalTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14.0f)
            binding.cokeTotalTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14.0f)
            binding.pepsiTotalTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14.0f)
            binding.waterTotalTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14.0f)
            binding.taxTotalTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14.0f)
            binding.pickupNoChargeTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14.0f)
            binding.tipTotalsTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14.0f)
            binding.finalTotalTextview.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20.0f)
            binding.cokeTextview.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16.0f)
            binding.pepsiTextview.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16.0f)
            binding.waterTextview.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16.0f)

            // RadioButtons
            binding.pepperoniRadioButton.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16.0f)
            binding.frontenacRadioButton.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16.0f)
            binding.napolitanaRadioButton.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16.0f)
            binding.classicCheeseRadioButton.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16.0f)
            binding.handTossedRadioButton.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16.0f)
            binding.thinCrustRadioButton.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16.0f)
            binding.tip10RadioButton.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16.0f)
            binding.tip15RadioButton.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16.0f)
            binding.tip20RadioButton.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16.0f)
            binding.pickupRadioButton.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16.0f)
            binding.deliveryRadioButton.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16.0f)
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
}