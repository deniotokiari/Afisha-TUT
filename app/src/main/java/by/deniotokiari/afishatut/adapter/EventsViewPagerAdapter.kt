package by.deniotokiari.afishatut.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.PagerAdapter
import by.deniotokiari.afishatut.fragment.EventsFragment
import by.deniotokiari.afishatut.extensions.newFragmentInstance

class EventsViewPagerAdapter(frm: FragmentManager) : FragmentStatePagerAdapter(frm) {

    private val categories: MutableList<String> = mutableListOf()

    override fun getItem(position: Int): Fragment {
        return newFragmentInstance<EventsFragment>("category" to categories[position])
    }

    override fun getItemPosition(`object`: Any): Int {
        return PagerAdapter.POSITION_NONE
    }

    override fun getPageTitle(position: Int): CharSequence? = categories[position]

    override fun getCount(): Int = categories.size

    fun updateItems(items: List<String>) {
        categories.clear()
        categories.addAll(items)

        notifyDataSetChanged()
    }

}