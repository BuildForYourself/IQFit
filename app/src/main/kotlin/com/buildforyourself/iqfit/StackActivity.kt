package com.buildforyourself.iqfit

import android.content.Context
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.*
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import com.buildforyourself.iqfit.data.DataProviderFactory
import com.buildforyourself.iqfit.model.Food
import com.buildforyourself.iqfit.util.DbGenerator
import org.jetbrains.anko.find
import org.jetbrains.anko.onItemClick
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

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


    }

    override fun onResume() {
        val lv = findViewById(R.id.list_view) as ListView

        lv.adapter = ListExampleAdapter(this)

        lv.onItemClick { adapterView, view, i, l ->
            val lea = lv.adapter as ListExampleAdapter
            val food = lea.getItem(i) as Food
            toast("Количество калорий ${food.calories}")
        }

        super.onResume()
    }

    private class ListExampleAdapter(context: Context) : BaseAdapter() {
        val foods: List<Food> = DataProviderFactory.instance.dataProvider.loadFood().sortedByDescending { f -> f.dateTime }
        private val mInflator: LayoutInflater

        init {
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
                vh = ListRowHolder(view, foods[position])
                view.tag = vh
            } else {
                view = convertView
                vh = view.tag as ListRowHolder
            }

            vh.category_label.text = foods[position].foodCategory.name
            vh.percent_label.text = foods[position].percent.toString() + "%"
            vh.imageView.setImageDrawable(foods[position].foodCategory.icon)
            //var t = Drawable.createFromPath("@color/design_fab_stroke_end_inner_color")

            //vh.icon = _foods[position].foodCategory.icon
            return view
        }
    }

    private class ListRowHolder(row: View?, food: Food) {
        var category_label: TextView
        val percent_label: TextView
        val food: Food
        var imageView: ImageView
        init {
            this.category_label = row?.findViewById(R.id.food_category_name) as TextView
            this.percent_label = row?.findViewById(R.id.percent) as TextView
            this.food = food
            this.imageView = row?.findViewById(R.id.icon) as ImageView
//            val context = IQFitApplication.instance.applicationContext
//            var drawable = ContextCompat.getDrawable(context, R.drawable.icon1);
//            this.icon = drawable
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
