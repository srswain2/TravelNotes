package com.appsnipp.travelnotes.cardLayoutList

import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.appsnipp.travelnotes.R

import java.util.ArrayList

class cardLayoutListActivity : AppCompatActivity() {
    private var layoutManager: RecyclerView.LayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.card_layout_activity_main)

        myOnClickListener = MyOnClickListener(this)

        recyclerView = findViewById(R.id.my_recycler_view) as RecyclerView
        recyclerView!!.setHasFixedSize(true)

        layoutManager = LinearLayoutManager(this)
        recyclerView!!.layoutManager = layoutManager
        recyclerView!!.itemAnimator = DefaultItemAnimator()

        data = ArrayList()
        for (i in MyData.nameArray.indices) {
            data!!.add(DataModel(
                    MyData.nameArray[i],
                    MyData.versionArray[i],
                    MyData.id_[i],
                    MyData.drawableArray[i]
            ))
        }

        removedItems = ArrayList()

        adapter = CustomAdapter(data!!)
        recyclerView!!.adapter = adapter
    }


    private class MyOnClickListener(private val context: Context) : View.OnClickListener {

        override fun onClick(v: View) {
            removeItem(v)
        }

        private fun removeItem(v: View) {
            val selectedItemPosition = recyclerView!!.getChildPosition(v)
            val viewHolder = recyclerView!!.findViewHolderForPosition(selectedItemPosition)
            val textViewName = viewHolder!!.itemView.findViewById(R.id.textViewName) as TextView
            val selectedName = textViewName.text as String
            var selectedItemId = -1
            for (i in MyData.nameArray.indices) {
                if (selectedName == MyData.nameArray[i]) {
                    selectedItemId = MyData.id_[i]
                }
            }
            removedItems!!.add(selectedItemId)
            data!!.removeAt(selectedItemPosition)
            adapter!!.notifyItemRemoved(selectedItemPosition)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)
        if (item.itemId == R.id.add_item) {
            //check if any items to add
            if (removedItems!!.size != 0) {
                addRemovedItemToList()
            } else {
                Toast.makeText(this, "Nothing to add", Toast.LENGTH_SHORT).show()
            }
        }
        return true
    }

    private fun addRemovedItemToList() {
        val addItemAtListPosition = 3
        data!!.add(addItemAtListPosition, DataModel(
                MyData.nameArray[removedItems!![0]],
                MyData.versionArray[removedItems!![0]],
                MyData.id_[removedItems!![0]],
                MyData.drawableArray[removedItems!![0]]
        ))
        adapter!!.notifyItemInserted(addItemAtListPosition)
        removedItems!!.removeAt(0)
    }

    companion object {
        private var adapter: RecyclerView.Adapter<*>? = null
        private var recyclerView: RecyclerView? = null
        private var data: ArrayList<DataModel>? = null
        internal lateinit var myOnClickListener: View.OnClickListener
        private var removedItems: ArrayList<Int>? = null
    }
}