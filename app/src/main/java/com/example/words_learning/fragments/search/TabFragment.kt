package com.example.words_learning.fragments.search

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.words_learning.R
import com.example.words_learning.Router

class TabFragment : Fragment() {

    private lateinit var router : Router
    val name = "Search"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val x = inflater.inflate(R.layout.tab_layout, null)

        tabLayout = x.findViewById<View>(R.id.tabs) as TabLayout
        viewPager = x.findViewById<View>(R.id.viewpager) as ViewPager

        viewPager.adapter = MyAdapter(childFragmentManager)
        tabLayout.post{tabLayout.setupWithViewPager(viewPager)}

        val listView = activity!!.findViewById<TextView>(R.id.textView2)
        listView.setText(name)

        return x
    }

    internal inner class MyAdapter (fm: FragmentManager): FragmentPagerAdapter(fm){
        override fun getItem(position: Int): Fragment? {
            when(position){
                0->return SearchWord()
                1->return SearchWord()
            }
            return null
        }

        override fun getCount(): Int {
            return items
        }

        override fun getPageTitle(position: Int): CharSequence? {
            when(position){
                0->return "En-Ru"
                1->return "Ru-En"
            }
            return null
        }
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var tabLayout: TabLayout
        @SuppressLint("StaticFieldLeak")
        lateinit var viewPager: ViewPager

        var items = 2
    }

    override fun onResume() {
        super.onResume()
        val listView = activity!!.findViewById<TextView>(R.id.textView2)
        listView.setText(name)
    }
}