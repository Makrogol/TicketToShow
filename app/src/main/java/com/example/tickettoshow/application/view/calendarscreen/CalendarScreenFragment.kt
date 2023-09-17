package com.example.tickettoshow.application.view.calendarscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.tickettoshow.foundation.views.BaseScreen
import com.example.tickettoshow.R
import com.example.tickettoshow.application.view.homescreen.HomeScreenFragment
import com.example.tickettoshow.application.view.profilescreen.ProfileFragment
import com.example.tickettoshow.databinding.FragmentCalendarBinding
import com.example.tickettoshow.foundation.tools.toInt
import com.example.tickettoshow.foundation.views.BaseFragment
import com.example.tickettoshow.foundation.views.screenViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class CalendarScreenFragment : BaseFragment(),
    BottomNavigationView.OnNavigationItemSelectedListener {

    class Screen : BaseScreen

    private lateinit var binding: FragmentCalendarBinding
    override val viewModel by screenViewModel<CalendarScreenViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCalendarBinding.inflate(inflater, container, false)
        binding.bottomNavMenu.menu.findItem(R.id.bot_menu_calendar_screen)
            .setIcon(R.drawable.calendar_on_icon)
        binding.bottomNavMenu.setOnNavigationItemSelectedListener(this)

//        with(binding.calendar) {
//            for (i in 0 until rowCount) {
//                for (j in 0 until columnCount) {
//                    println("$i $j ${getChildAt(i * rowCount + j)}")
//                }
//            }
//        }
//
//        val currData = viewModel.calculateCurrData()
//        fillCalendar(currData[0], currData[1], currData[2])
//
//
//
//        with(binding) {
//            val prevMonth = currData[1]
//            leftButton.setOnClickListener {
//                currData[1] = currData[1] - 1 + 11 * (prevMonth == 0).toInt()
//                currData[0] -= (prevMonth == 1).toInt()
//                currData[2] = viewModel.calculateStartMonth(
//                    countDayInMonth[prevMonth] + 1 + (currData[0] % 4 == 0 && currData[1] == 1).toInt(),
//                    currData[2]
//                )
//                fillCalendar(currData[0], currData[1], currData[2])
//            }
//            rightButton.setOnClickListener {
//                currData[1] = (currData[1] + 1) % 12
//                currData[0] += (prevMonth == 11).toInt()
//                currData[2] = viewModel.calculateStartMonth(
//                    35 - (countDayInMonth[prevMonth] + 1 + (currData[0] % 4 == 0 && prevMonth == 1).toInt()),
//                    currData[2]
//                )
//                fillCalendar(currData[0], currData[1], currData[2])
//            }
//
//            refButton.setOnClickListener {
//                refLayout.visibility = View.VISIBLE
//            }
//            root.setOnClickListener {
//                refLayout.visibility = View.GONE
//            }
//        }

        return binding.root
    }
//
//    private fun fillCalendar(year: Int, month: Int, dayOfWeekStartMonth: Int) {
//        fillCells(dayOfWeekStartMonth, month)
//        binding.monthYearTextview.text = getString(R.string.month_year_text, months[month], year)
//    }
//
//
//    private fun fillCells(dayOfWeekStartMonth: Int, month: Int) {
//        var currDay = 1
//        with(binding.calendar) {
//            var row = 2
//            hideSomeFirstDay(row, dayOfWeekStartMonth)
//
//            for (j in dayOfWeekStartMonth until binding.calendar.columnCount - 1) {
//                fillDay(row, j, currDay, month)
//                currDay++
//            }
//
//            for (i in 5 until rowCount - 1 step 3) {
//                for (j in 0 until columnCount - 1) {
//                    fillDay(i, j, currDay, month)
//                    currDay++
//                }
//            }
//
//            row = 17
//            for (j in 0..2) {
//                fillDay(row, j, currDay, month)
//                currDay++
//            }
//        }
//    }
//
//    private fun hideDay(row: Int, col: Int) = with(binding.calendar) {
//        if (getChildAt(row * columnCount + col) != null)
//            getChildAt(row * columnCount + col).visibility = View.INVISIBLE
//        if (getChildAt((row + 1) * columnCount + col) != null)
//            getChildAt((row + 1) * columnCount + col).visibility = View.INVISIBLE
//        if (getChildAt((row + 2) * columnCount + col) != null)
//            getChildAt((row + 2) * columnCount + col).visibility = View.INVISIBLE
//    }
//
//    private fun hideSomeFirstDay(row: Int, dayOfWeekStartMonth: Int) {
//        for (j in 0..dayOfWeekStartMonth) {
//            hideDay(row, j)
//        }
//    }
//
//    private fun fillDay(row: Int, col: Int, currDay: Int, month: Int) = with(binding.calendar) {
//        if (currDay > countDayInMonth[month]) {
//            hideDay(row, col)
//            return
//        }
//
//        if (getChildAt(row * columnCount + col) is TextView)
//            (getChildAt(row * columnCount + col) as TextView).text = currDay.toString()
//
//        if (getChildAt((row + 1) * columnCount + col) is TextView)
//            (getChildAt((row + 1) * columnCount + col) as TextView).text = dayOfWeek[col]
//
//        var currColor = resources.getColor(R.color.black, null)
//        if (currDay in nationalHolidays[month])
//            currColor = resources.getColor(R.color.date_public_holidays_color, null)
//        if (getChildAt((row + 2) * columnCount + col) is TextView)
//            (getChildAt((row + 2) * columnCount + col) as TextView).setBackgroundColor(currColor)
//    }
//
    override fun onNavigationItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.bot_menu_home_screen -> viewModel.launch(HomeScreenFragment.Screen())
        R.id.bot_menu_profile_screen -> viewModel.launch(ProfileFragment.Screen())
        R.id.bot_menu_calendar_screen -> viewModel.launch(Screen())
        else -> false
    }
//
//    companion object {
//        val nationalHolidays = arrayOf(
//            arrayOf(1, 2, 3, 4, 5, 6, 7, 8), //январь
//            arrayOf(23), //февраль
//            arrayOf(8), //март
//            arrayOf(), //апрель
//            arrayOf(1, 9), //май
//            arrayOf(12), //июнь
//            arrayOf(), //июль
//            arrayOf(), //август
//            arrayOf(), //сентябрь
//            arrayOf(), //октябрь
//            arrayOf(4), //ноябрь
//            arrayOf() //декабрь
//        )
//        val dayOfWeek = arrayOf("пн", "вт", "ср", "чт", "пт", "сб", "вс")
//
//        val months = arrayOf(
//            "Январь",
//            "Февраль",
//            "Март",
//            "Апрель",
//            "Май",
//            "Июнь",
//            "Июль",
//            "Август",
//            "Сентябрь",
//            "Октябрь",
//            "Ноябрь",
//            "Декабрь"
//        )
//
//        val countDayInMonth = arrayOf(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)
//    }
}