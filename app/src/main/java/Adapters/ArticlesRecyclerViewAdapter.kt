package Adapters

import CodeUtilities.CodeUtil
import Pojo.Result
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import debs.cora.nytimesarticlaes.ItemDetailActivity
import debs.cora.nytimesarticlaes.ItemDetailFragment
import debs.cora.nytimesarticlaes.ItemListActivity
import debs.cora.nytimesarticlaes.R
import kotlinx.android.synthetic.main.single_row_article.view.*


class ArticlesRecyclerViewAdapter(
    private val parentActivity: ItemListActivity,
    private var values: List<Result>,
    private val twoPane: Boolean
) :
    RecyclerView.Adapter<ArticlesRecyclerViewAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.titleView.text = item.title
        holder.subtitleView.text = item.abstract
        holder.dateView.text = item.publishedDate
        holder.byView.text = item.source

        //set the tag of the NewsPaper image button to the item,'s url
        holder.goToUrlImageButton.tag = item.url

        //Load image url into image view
        CodeUtil.loadImage(
            holder.profileView,
            item.media?.get(0)?.mediaMetadata?.get(0)?.url.toString()
        )

        with(holder.itemView) {
            tag = item
            setOnClickListener(onClickListener)
        }
    }

    private val onClickListener: View.OnClickListener

    init {
        onClickListener = View.OnClickListener { v ->
            val item = v.tag as Result
            if (twoPane) {
                val fragment = ItemDetailFragment().apply {

                    arguments = Bundle().apply {
                        putSerializable("Result", item)
                    }
                }
                parentActivity.supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.item_detail_container, fragment)
                    .commit()
            } else {
                val intent = Intent(v.context, ItemDetailActivity::class.java).apply {
                    this.putExtra("Result", item)
                }
                v.context.startActivity(intent)
            }
        }
    }

    fun setData(values: List<Result>){
        this.values = values
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.single_row_article, parent, false)
        return ViewHolder(view)
    }


    override fun getItemCount() = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        val titleView: TextView = view.singleItemTitleTextView
        val subtitleView: TextView = view.singleItemSubTitleTextView
        val byView: TextView = view.singleItemByTextView
        val dateView: TextView = view.singleItemDateTextView
        val profileView: ImageView = view.singleItemImageView
        val goToUrlImageButton: ImageButton = view.goToUrlImageButton

        init {
            goToUrlImageButton.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            if (v != null) {
                val browserIntent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(v.tag as String?)
                )

                startActivity(v.context, browserIntent, null)
            }
        }
    }
}