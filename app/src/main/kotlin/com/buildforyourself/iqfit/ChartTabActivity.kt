package com.buildforyourself.iqfit

import android.graphics.Color
import android.graphics.Typeface
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
import com.buildforyourself.iqfit.data.DataProviderFactory

import com.buildforyourself.iqfit.data.FakeDataProvider
import com.buildforyourself.iqfit.model.Food
import com.buildforyourself.iqfit.model.FoodCategory
import com.buildforyourself.iqfit.model.FoodComponent
import lecho.lib.hellocharts.model.*

import lecho.lib.hellocharts.util.ChartUtils
import lecho.lib.hellocharts.view.ColumnChartView
import lecho.lib.hellocharts.view.ComboLineColumnChartView
import lecho.lib.hellocharts.view.LineChartView
import lecho.lib.hellocharts.view.PieChartView
import java.util.*

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
        private val hasLabels = true

        private val hasLabelsOutside = false
        private val hasCenterCircle = true
        private val hasCenterText1 = true
        private val hasCenterText2 = true
        private val isExploded = true
        private val hasLabelForSelected = false

        override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                                  savedInstanceState: Bundle?): View? {
            val rootView = inflater!!.inflate(R.layout.fragment_chart_tab, container, false)
//            val textView = rootView.findViewById(R.id.section_label) as TextView
//            textView.text = getString(R.string.section_format, arguments.getInt(ARG_SECTION_NUMBER))

            val sectionNumber = arguments.getInt(ARG_SECTION_NUMBER)
            val lineColumnChart = rootView.findViewById(R.id.linecolumnchart) as ComboLineColumnChartView;
            val pieChart = rootView.findViewById(R.id.piechart) as PieChartView;
            val foods = DataProviderFactory.instance.dataProvider.loadFood()
            //val foods = FakeDataProvider().loadFood()

            if (sectionNumber == 1){
                pieChart.visibility = 1
                lineColumnChart.visibility = -1

                SetPieChartData(pieChart, sectionNumber, foods)
            } else{
                lineColumnChart.visibility = 1
                pieChart.visibility = -1

                SetLineColumnChartData(lineColumnChart, sectionNumber, foods)
            }

            return rootView
        }

        private fun SetPieChartData(chart: PieChartView, sectionNumber: Int, foods: List<Food>) {

            val values = mutableListOf<SliceValue>()
//            for (i in 0..numValues) {
//                val sliceValue = SliceValue((Math.random() * 30 + 15).toFloat(), ChartUtils.pickColor());
//                values.add(sliceValue);
//            }

            val items = foods
            val legendItems = mutableListOf<LegendItem>()

            var categories = mutableListOf<FoodCategory>()
            for(i in 0..items.count() - 1){
                if (categories.find { it.name == items[i].foodCategory.name } == null)
                    categories.add(items[i].foodCategory)
            }

            for(i in 0..categories.count() - 1){
                var value = items.filter { it.foodCategory.name == categories[i].name }.sumBy { it.percent }.toFloat()

                val color = ChartUtils.pickColor()
                val legendItem = LegendItem(categories[i].name, value, color)
                legendItems.add(legendItem)

                val sliceValue = SliceValue(value, color)
                values.add(sliceValue)
            }

            val data = PieChartData(values);
            data.setHasLabels(hasLabels);
            data.setHasLabelsOnlyForSelected(hasLabelForSelected);
            data.setHasLabelsOutside(hasLabelsOutside);
            data.setHasCenterCircle(hasCenterCircle);

            if (isExploded) {
                data.setSlicesSpacing(4);
            }

            if (hasCenterText1) {
                data.setCenterText1("%");

                // Get roboto-italic font.
                val tf = Typeface.createFromAsset(getActivity().getAssets(), "Roboto-Italic.ttf");
                data.setCenterText1Typeface(tf);

                // Get font size from dimens.xml and convert it to sp(library uses sp values).
                data.setCenterText1FontSize(20);
            }

            if (hasCenterText2) {
                data.setCenterText2("Категории");

                val tf = Typeface.createFromAsset(getActivity().getAssets(), "Roboto-Italic.ttf");

                data.setCenterText2Typeface(tf);
                data.setCenterText2FontSize(14);
            }

            chart.setPieChartData(data)
        }

        private fun SetLineColumnChartData(chart: ComboLineColumnChartView, sectionNumber: Int, foods: List<Food>) {

            var numCount = 0

            if (sectionNumber == 2) {
                numCount = 31
            } else if (sectionNumber == 3) {
                numCount = 12
            }

            generateValues(numCount)

            val data = ComboLineColumnChartData(generateColumnData(foods, sectionNumber), generateLineData(numCount))

            setAxes(data, sectionNumber)

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

        private fun generateColumnData(foods: List<Food>, sectionNumber: Int): ColumnChartData {

            var numColumns = 0

            var items = mutableListOf<Food>()

            if (sectionNumber == 2) {
                for(i in 0..foods.count() - 1){

                    val date= foods[i].dateTime
                    val cal = Calendar.getInstance();
                    cal.setTime(date);
                    val month = cal.get(Calendar.MONTH);

                    if (month == 3) // January = 0
                        items.add(foods[i])
                }

                numColumns = 31// items.count()
            } else if (sectionNumber == 3) {
                items.addAll(foods);
                numColumns = 12
            }

            for(i in 0..foods.count()){

            }

            val columns = ArrayList<Column>()
            var values: List<SubcolumnValue>

            for (i in 1..numColumns - 1) {

                values = ArrayList<SubcolumnValue>()

                    if (sectionNumber == 2) {
                        var count = items.count()
                        if (count != 0) {

                            var filtered = mutableListOf<Food>()

                            for(j in 0..count-1){
                                val item = items[j];

                                val date= foods[j].dateTime
                                val cal = Calendar.getInstance();
                                cal.setTime(date);
                                val day = cal.get(Calendar.DAY_OF_MONTH);

                                if (day == i)
                                    filtered.add(item)
                            }

                            var percent = filtered.sumBy { it.percent }.toFloat()

                            values.add(SubcolumnValue(percent, ChartUtils.COLOR_GREEN))
                        }
                    } else if (sectionNumber == 3) {
                        val filtered = items.filter { it.dateTime.month == i };
                        val count = filtered.count()
                        if (count != 0)
                            values.add(SubcolumnValue((filtered.sumBy { it.percent }).toFloat(), ChartUtils.COLOR_GREEN));
                    }

                val column = Column(values)

                column.setHasLabels(hasLabels)
                column.setHasLabelsOnlyForSelected(true)

                columns.add(column)
            }

            return ColumnChartData(columns)
        }

        private fun setAxes(data: AbstractChartData, sectionNumber: Int) {
            if (hasAxes) {
                val axisX = Axis()
                val axisY = Axis().setHasLines(true)
                if (hasAxesNames) {

                    var xName = ""
                    var yName = ""

                    if (sectionNumber == 2) {
                        xName = "Дни"
                        yName = "%"
                    } else if (sectionNumber == 3) {
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
            // Show 3 total pages.
            return 3
        }

        override fun getPageTitle(position: Int): CharSequence? {
            when (position) {
                1 -> return "Месяц"
                2 -> return "Год"
            }
            return null
        }
    }

    class LegendItem(val name: String, val float: Float, val color: Int){
    }
}
