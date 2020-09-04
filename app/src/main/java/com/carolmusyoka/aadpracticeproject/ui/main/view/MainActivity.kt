package com.carolmusyoka.aadpracticeproject.ui.main.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.carolmusyoka.aadpracticeproject.R
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {
    lateinit var viewPager: ViewPager
    lateinit var tabLayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        setStatePageAdapter()

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
                val fm = supportFragmentManager
                val ft = fm.beginTransaction()
                val count = fm.backStackEntryCount
                if (count >= 1) {
                    supportFragmentManager.popBackStack()
                }
                ft.commit()
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                // setAdapter();


            }

            override fun onTabReselected(tab: TabLayout.Tab) {

                //   viewPager.notifyAll();
            }
        })
    }

    private fun  initViews(){
        viewPager = findViewById(R.id.pager)
        tabLayout = findViewById(R.id.tabs)
    }

    private fun setStatePageAdapter(){
        val myViewPageStateAdapter: MyViewPageStateAdapter = MyViewPageStateAdapter(supportFragmentManager)
        myViewPageStateAdapter.addFragment(LearningFragment(),"Learning Leaders")
        myViewPageStateAdapter.addFragment(SkillFragment(),"Skill IQ Leaders ")
        viewPager.adapter=myViewPageStateAdapter
        tabLayout.setupWithViewPager(viewPager,true)

    }
    class MyViewPageStateAdapter(fm: FragmentManager): FragmentStatePagerAdapter(fm){
        val fragmentList:MutableList<Fragment> = ArrayList<Fragment>()
        val fragmentTitleList:MutableList<String> = ArrayList<String>()

        override fun getItem(position: Int): Fragment {
            return fragmentList.get(position)
        }

        override fun getCount(): Int {
            return fragmentList.size
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return fragmentTitleList.get(position)
        }

        fun addFragment(fragment: Fragment, title:String){
            fragmentList.add(fragment)
            fragmentTitleList.add(title)

        }
    }
}


