package com.example.cartlist

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
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

        // Register ListView for context menu
        registerForContextMenu(listView)

        // Example input fields and button to add new products
        val nameInput: EditText = findViewById(R.id.editProductText)
        val quantityInput: EditText = findViewById(R.id.editQuantityText)
        val priceInput: EditText = findViewById(R.id.editPriceText)
        val addButton: Button = findViewById(R.id.addItemButton)

        // Button click listener to add a new product
        addButton.setOnClickListener {
            val name = nameInput.text.toString()

            // condition to check if the name is empty
            if (name.isEmpty()) {
                Toast.makeText(this, "Please enter a product name", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

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

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        if (v?.id == R.id.cartListView) {
            menu?.add(0, v.id, 0, "Delete")
        }
    }

    // Handle context menu item selection
    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.title) {
            "Delete" -> {
                // Get the position of the item to delete
                val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
                val position = info.position
                productList.removeAt(position)
                productAdapter.notifyDataSetChanged() // Update ListView
                Toast.makeText(this, "Product removed", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }
}
