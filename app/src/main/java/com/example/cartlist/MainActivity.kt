package com.example.cartlist

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var productList: MutableList<Products>
    private lateinit var productAdapter: ProductAdapter

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize your product list and adapter
        productList = mutableListOf()
        productAdapter = ProductAdapter(this, productList)

        // Set up the ListView and adapter
        val listView: ListView = findViewById(R.id.cartListView)
        listView.adapter = productAdapter

        // Example input fields and button to add new products
        val nameInput: EditText = findViewById(R.id.editProductText)
        val quantityInput: EditText = findViewById(R.id.editQuantityText)
        val priceInput: EditText = findViewById(R.id.editPriceText)
        val addButton: Button = findViewById(R.id.addItemButton)

        // Button click listener to add a new product
        addButton.setOnClickListener {
            val name = nameInput.text.toString()
            val quantity = quantityInput.text.toString().toIntOrNull() ?: 0
            val price = priceInput.text.toString().toFloatOrNull() ?: 0.0f

            addProduct(name, quantity, price)
        }
    }

    // Function to add a product and update the adapter
    private fun addProduct(name: String, quantity: Int, price: Float) {
        val product = Products(name, quantity, price)
        productList.add(product)
        productAdapter.notifyDataSetChanged() // Update the ListView
    }
}