package debs.cora.nytimesarticlaes

import Adapters.ArticlesRecyclerViewAdapter
import NetworkHelper.ArticlesViewModel
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.size
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_item_list.*
import kotlinx.android.synthetic.main.item_list.*


class ItemListActivity : AppCompatActivity() {


    private var twoPane: Boolean = false
    private val mArticlesViewModel: ArticlesViewModel = ArticlesViewModel()
    private var mRVAdapter: ArticlesRecyclerViewAdapter? = null

    init {
        //calling NY Times URL End point to fill the array with data
        mArticlesViewModel.createArticleLiveData()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_list)


        setSupportActionBar(toolbar)
        toolbar.title = title


        if (item_detail_container != null) {
            twoPane = true
        }

        mArticlesViewModel.getMutableLiveData().observe(this, Observer {
            // check if data is loaded for the first time
            if(NYTimesArticlesRecycleView.size == 0){
                setupRecyclerView(NYTimesArticlesRecycleView)
            }
            else {
                // live data is modified/changed so we notify the recycler view
                //to update the data displayed
                //we can run a loop to check the items that changed
                mRVAdapter?.setData(mArticlesViewModel.getMutableLiveData().value!!)
                mRVAdapter?.notifyDataSetChanged()
            }

        })
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        mRVAdapter = ArticlesRecyclerViewAdapter(
            this,
            mArticlesViewModel.getMutableLiveData().value!!, twoPane
        )
        recyclerView.adapter = mRVAdapter
    }
}
