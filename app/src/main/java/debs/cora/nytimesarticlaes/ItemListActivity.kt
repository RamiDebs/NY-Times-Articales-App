package debs.cora.nytimesarticlaes

import NetworkHelper.ArticlesViewModel
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.size
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import debs.cora.nytimesarticlaes.ui.adapters.ArticlesRecyclerViewAdapter
import fragments.ItemDetailFragment
import kotlinx.android.synthetic.main.activity_item_list.*
import kotlinx.android.synthetic.main.item_list.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ItemListActivity : AppCompatActivity() {

    // twoPane is a boolean that represents if the app in landscape (Big screen device) or portrait mode single activity
    //so we check if it is a tablet we set it true and we lunch the big layout with the fragment aside
    private var twoPane: Boolean = false
    private val viewModel: ArticlesViewModel = ArticlesViewModel()
    private var adapter: ArticlesRecyclerViewAdapter? = null

    init {
        //calling NY Times URL End point to fill the array with data
        viewModel.createArticleLiveData()

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_list)


        setSupportActionBar(toolbar)
        toolbar.title = title

        //check if the container of the fragment is different from null so we know that it is a tablet
        if (item_detail_container != null) {
            twoPane = true
        }

        //running api request on background thread
        CoroutineScope(IO).launch {
            startApiRequest()
        }


    }

    private suspend fun startApiRequest() {
        //do UI things on App the Main thread
        withContext(Main) {
            viewModel.getMutableLiveData().observe(this@ItemListActivity, Observer {

                // check if data is loaded for the first time
                if (NYTimesArticlesRecycleView.size == 0) {

                    setupRecyclerView(NYTimesArticlesRecycleView)

                } else {
                    // live data is modified/changed so we notify the recycler view
                    //to update the data displayed
                    //we can run a loop to check the items that changed
                    adapter?.setData(viewModel.getMutableLiveData().value!!)
                    adapter?.notifyDataSetChanged()
                }
            })
        }
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        viewModel.getMutableLiveData().value?.let {
            adapter = ArticlesRecyclerViewAdapter(it)
        }
        adapter?.setOnImageClicked {
            val browserIntent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse(it.url)
            )
            startActivity(browserIntent)
        }

        adapter?.setOnItemClicked { item ->
            if (twoPane) {
                val fragment = ItemDetailFragment().apply {
                    arguments = Bundle().apply {
                        putSerializable("Result", item)
                    }
                }
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.item_detail_container, fragment)
                    .commit()
            } else {
                val intent = Intent(ItemListActivity@ this, ItemDetailActivity::class.java).apply {
                    this.putExtra("Result", item)
                }
                ItemListActivity@ this.startActivity(intent)
            }
        }
        recyclerView.adapter = adapter

    }
}
