package com.buildforyourself.iqfit

import android.graphics.Color
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup

import android.widget.RelativeLayout
import android.widget.TextView

import com.buildforyourself.iqfit.data.FakeDataProvider
import com.buildforyourself.iqfit.model.Food
import com.buildforyourself.iqfit.model.FoodCategory
import com.buildforyourself.iqfit.model.FoodComponent

import java.util.ArrayList

import lecho.lib.hellocharts.model.AbstractChartData
import lecho.lib.hellocharts.model.Axis
import lecho.lib.hellocharts.model.Column
import lecho.lib.hellocharts.model.ColumnChartData
import lecho.lib.hellocharts.model.ComboLineColumnChartData
import lecho.lib.hellocharts.model.Line
import lecho.lib.hellocharts.model.LineChartData
import lecho.lib.hellocharts.model.PointValue
import lecho.lib.hellocharts.model.SubcolumnValue
import lecho.lib.hellocharts.util.ChartUtils
import lecho.lib.hellocharts.view.ColumnChartView
import lecho.lib.hellocharts.view.ComboLineColumnChartView
import lecho.lib.hellocharts.view.LineChartView

class ChartTabActivity : AppCompatActivity() {

    /**
     * The [android.support.v4.view.PagerAdapter] that will provide
     * fragments for each of the sections. We use a
     * [FragmentPagerAdapter] derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * [android.support.v4.app.FragmentStatePagerAdapter].
     */
    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null

    /**
     * The [ViewPager] that will host the section contents.
     */
    private var mViewPager: ViewPager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chart_tab)

        val toolbar = findViewById(R.id.toolbar) as Toolbar?
        setSupportActionBar(toolbar)
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)

        // Set up the ViewPager with the sections adapter.
        mViewPager = findViewById(R.id.container) as ViewPager?
        mViewPager!!.adapter = mSectionsPagerAdapter
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_chart_tab, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    class PlaceholderFragment : Fragment() {

        private val numberOfLines = 1
        private val maxNumberOfLines = 1
        private val numberOfPoints = 31

        internal var randomNumbersTab = Array(maxNumberOfLines) { FloatArray(numberOfPoints) }

        private val hasAxes = true
        private val hasAxesNames = true
        private val hasPoints = false
        private val hasLines = true
        private val isCubic = false
        private val hasLabels = false

        override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                                  savedInstanceState: Bundle?): View? {
            val rootView = inflater!!.inflate(R.layout.fragment_chart_tab, container, false)
//            val textView = rootView.findViewById(R.id.section_label) as TextView
//            textView.text = getString(R.string.section_format, arguments.getInt(ARG_SECTION_NUMBER))

            SetLineColumnChartData(rootView.findViewById(R.id.linecolumnchart) as ComboLineColumnChartView, arguments.getInt(ARG_SECTION_NUMBER))

            return rootView
        }

        private fun SetLineColumnChartData(chart: ComboLineColumnChartView, anInt: Int) {

            var numCount = 0

            if (anInt == 1) {
                numCount = 31
            } else if (anInt == 2) {
                numCount = 12
            }

            val foods = FakeDataProvider().loadFood()

            generateValues(numCount)

            val data = ComboLineColumnChartData(generateColumnData(foods, anInt), generateLineData(numCount))

            setAxes(data, anInt)

            chart.comboLineColumnChartData = data
        }

        private fun generateValues(numCount: Int) {
            for (i in 0..maxNumberOfLines - 1) {
                for (j in 0..numCount - 1) {
                    randomNumbersTab[i][j] = 100f
                }
            }
        }

        private fun generateLineData(numCount: Int): LineChartData {
            val lines = ArrayList<Line>()

            for (i in 0..0) {

                val values = ArrayList<PointValue>()
                for (j in 0..numCount - 1) {
                    values.add(PointValue(j.toFloat(), randomNumbersTab[i][j]))
                }

                val line = Line(values)
                line.color = ChartUtils.COLORS[i]
                line.isCubic = isCubic
                line.setHasLabels(hasLabels)
                line.setHasLines(hasLines)
                line.setHasPoints(hasPoints)
                lines.add(line)
            }

            val lineChartData = LineChartData(lines)

            return lineChartData
        }

        private fun generateColumnData(foods: List<Food>, anInt: Int): ColumnChartData {
            val numSubcolumns = 1
            var numColumns = 0

            var items = mutableListOf<Food>()

            if (anInt == 1) {
                items = foods.filter { it.dateTime.month == 4}.toMutableList()
                numColumns = items.count()

            } else {
                items.addAll(foods);
                numColumns = 12
            }

            val columns = ArrayList<Column>()
            var values: List<SubcolumnValue>

            for (i in 1..numColumns - 1) {

                values = ArrayList<SubcolumnValue>()

                    if (anInt == 1) {
                        values.add(SubcolumnValue((items.sumBy { it.calories }/items.count()).toFloat(), ChartUtils.COLOR_GREEN));
                    } else {
                        val filtered = items.filter { it.dateTime.month == i };
                        values.add(SubcolumnValue((filtered.sumBy { it.calories }/filtered.count()).toFloat(), ChartUtils.COLOR_GREEN));
                    }

                val column = Column(values)

                column.setHasLabels(true)
                column.setHasLabelsOnlyForSelected(true)

                columns.add(column)
            }

            return ColumnChartData(columns)
        }

        private fun setAxes(data: AbstractChartData, anInt: Int) {
            if (hasAxes) {
                val axisX = Axis()
                val axisY = Axis().setHasLines(true)
                if (hasAxesNames) {

                    var xName = ""
                    var yName = ""

                    if (anInt == 1) {
                        xName = "Дни"
                        yName = "%"
                    } else if (anInt == 2) {
                        xName = "Месяцы"
                        yName = "%"
                    }

                    axisX.name = xName
                    axisY.name = yName
                }
                data.axisXBottom = axisX
                data.axisYLeft = axisY
            } else {
                data.axisXBottom = null
                data.axisYLeft = null
            }
        }

        companion object {
            /**
             * The fragment argument representing the section number for this
             * fragment.
             */
            private val ARG_SECTION_NUMBER = "section_number"

            /**
             * Returns a new instance of this fragment for the given section
             * number.
             */
            fun newInstance(sectionNumber: Int): PlaceholderFragment {
                val fragment = PlaceholderFragment()
                val args = Bundle()
                args.putInt(ARG_SECTION_NUMBER, sectionNumber)
                fragment.arguments = args

                return fragment
            }
        }
    }

    /**
     * A [FragmentPagerAdapter] that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    inner class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1)
        }

        override fun getCount(): Int {
            // Show 2 total pages.
            return 2
        }

        override fun getPageTitle(position: Int): CharSequence? {
            when (position) {
                0 -> return "Месяц"
                1 -> return "Год"
            }
            return null
        }
    }
}
