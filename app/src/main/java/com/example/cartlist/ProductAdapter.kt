package com.example.cartlist

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class ProductAdapter(private val context: Context, private val productList: MutableList<Products>) : BaseAdapter() {

    override fun getCount(): Int = productList.size

    override fun getItem(position: Int): Any = productList[position]

    override fun getItemId(position: Int): Long = position.toLong()

    @SuppressLint("SetTextI18n")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = convertView ?: LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        val product = productList[position]

        val productNameTextView: TextView = view.findViewById(R.id.productNameTextView)
        val productDetailsTextView: TextView = view.findViewById(R.id.productDetailsTextView)

        productNameTextView.text = product.productName
        productDetailsTextView.text = "Quantity: ${product.quantity}, Price: ${product.price}, Total: ${product.quantity * product.price}"

        return view
    }
}
