package com.buildforyourself.iqfit

import android.content.Context
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.ListViewCompat
import android.support.v7.widget.Toolbar
import android.view.*
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import com.buildforyourself.iqfit.data.FakeDataProvider
import com.buildforyourself.iqfit.model.Food
import com.buildforyourself.iqfit.util.DbGenerator
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity

class StackActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stack)
        val toolbar = find<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val fab = find<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            startActivity<FoodCategoriesActivity>()
        }

        val drawer = find<DrawerLayout>(R.id.drawer_layout)
        val toggle = ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()

        val navigationView = find<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        val lv = findViewById(R.id.list_view) as ListView
        lv.adapter = ListExampleAdapter(this)


    }

    private class ListExampleAdapter(context: Context) : BaseAdapter() {
        internal var foods: List<Food>
        private val mInflator: LayoutInflater

        init {
            val dataProvider = FakeDataProvider()
            foods = dataProvider.loadFood();
            this.mInflator = LayoutInflater.from(context)
        }

        override fun getCount(): Int {
            return foods.size
        }

        override fun getItem(position: Int): Any {
            return foods[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
            val view: View?
            val vh: ListRowHolder
            if (convertView == null) {
                view = this.mInflator.inflate(R.layout.food_layout, parent, false)
                vh = ListRowHolder(view, position)
                view.tag = vh
            } else {
                view = convertView
                vh = view.tag as ListRowHolder
            }

            vh.category_label.text = foods[position].foodCategory.name
            vh.percent_label.text = foods[position].percent.toString() + "%"
            vh.color
            //vh.icon.drawable = sList[position].foodCategory.icon
            return view
        }
    }

    private class ListRowHolder(row: View?, position: Int) {
        val category_label: TextView
        val percent_label: TextView
        val color: Int
        //val icon: ImageView
        init {
            var col = android.R.color.background_dark

            this.category_label = row?.findViewById(R.id.food_category_name) as TextView
            this.percent_label = row?.findViewById(R.id.percent) as TextView
            if ((position / 2) == 0) col = android.R.color.background_light
            this.color = col
            //this.icon = row?.findViewById(R.id.icon) as ImageView
        }
    }







    fun createFoodItems()
    {
        val scrollView = find<ListViewCompat>(R.id.list_view)
        val dataProvider = FakeDataProvider()
        //scrollView.adapter = dataProvider.loadFood();
        val foods = dataProvider.loadFood()

        for (food in foods)
        {

        }

    }

    override fun onBackPressed() {
        val drawer = find<DrawerLayout>(R.id.drawer_layout)
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.stack, menu)
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

    @SuppressWarnings("StatementWithEmptyBody")
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        val id = item.itemId

        if (id == R.id.nav_camera) {
            var generator = DbGenerator()
            generator.clear();
            generator.fill();
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_charts) {
            startActivity<ChartTabActivity>()
        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        val drawer = find<DrawerLayout>(R.id.drawer_layout)
        drawer.closeDrawer(GravityCompat.START)
        return true
    }
}
