package com.ashawooden.kotlindemo

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.layout_fragment_first.*
import kotlinx.android.synthetic.main.layout_fragment_first.view.*
import java.io.InputStream

class FirstFragment : Fragment() {
    init {
        Log.e("FirstFragment", "FirstFragmentCreated")
    }

    companion object {
        val instance: FirstFragment by lazy { FirstFragment() }
    }

    val mAndroidList: ArrayList<Any> = ArrayList()
    lateinit var mDataBase: FirebaseDatabase
    lateinit var mDataBaseRef: DatabaseReference

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v: View = inflater.inflate(R.layout.layout_fragment_first, container, false)
        mDataBase = FirebaseDatabase.getInstance()
        mDataBaseRef = mDataBase.getReference("खण्ड")

        val gridLayout = GridLayoutManager(activity, 1)

        v.rvBasicItem.setHasFixedSize(true)
        v.rvBasicItem?.viewTreeObserver?.addOnGlobalLayoutListener {
            rvBasicItem.viewTreeObserver.removeOnGlobalLayoutListener { activity }
            val width: Int = rvBasicItem.measuredWidth
            val newSpanCount: Int = Math.floor((width / 400).toDouble()).toInt()
            gridLayout.spanCount = newSpanCount
            gridLayout.requestLayout()
        }
        v.rvBasicItem.layoutManager = gridLayout
        /*rvBasicItem.adapter = BasicAdapter(this, mAndroidList) {
            Toast.makeText(this, "hello ${it}", Toast.LENGTH_LONG).show()
        }*/

        val adapter = object : GenericAdapter<Any>(mAndroidList) {
            override fun onClick(position: Int) {
                Toast.makeText(activity, "hello $position", Toast.LENGTH_LONG).show()
                /*var i:Intent = Intent(activity,SecondActivity::class.java).apply {
                    putExtra("","")
                    putExtra("","")
                    startActivity(this)
                }*/
            }

            override fun getViewHolder(view: View, viewType: Int): RecyclerView.ViewHolder {
                return GenericViewHolderFactory.create(view, viewType)
            }

            override fun getLayoutId(position: Int, obj: Any): Int {
                return when (obj) {
                    //is String -> R.layout.list_basic_item
                    //is User -> R.layout.list_user_item
                    is Shlok -> R.layout.list_basic_item
                    else -> R.layout.list_basic_item
                }
            }
        }
        v.rvBasicItem.adapter = adapter
        mDataBaseRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(p0: DataSnapshot) {
                val children = p0!!.children
                var i: Int = 1
                children.forEach {
                    mAndroidList.add(Shlok(i, it.key.toString()))
                    ++i
                }
                adapter!!.notifyDataSetChanged()
            }
        })
        return v
    }

    fun loadJSONFromAsset(): String? {
        var json: String? = null
        try {
            val inputStream: InputStream = activity!!.assets.open("data.json")
            json = inputStream.bufferedReader().use { it.readText() }
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
        return json
    }

    private fun addToList() {
        mAndroidList.add("Cupcake")
        mAndroidList.add(User("Karan", R.drawable.ic_mood))
        mAndroidList.add(".....")
        mAndroidList.add("Eclair")
        mAndroidList.add(User("Jay", R.drawable.ic_face))
        mAndroidList.add("Froyo")
        mAndroidList.add(User("Priyanshu", R.drawable.ic_face))
        mAndroidList.add("Gingerbread")
        mAndroidList.add("Honeycomb")
        mAndroidList.add("Ice Cream Sandwich")
        mAndroidList.add(User("Sagar", R.drawable.ic_mood))
        mAndroidList.add("Jelly Bean")
        mAndroidList.add("KitKat")
        mAndroidList.add("Lollipop")
        mAndroidList.add(User("Vikas", R.drawable.ic_face))
        mAndroidList.add("Marshmallow")
        mAndroidList.add("Nougat")
        mAndroidList.add("Oreo")
    }

}
