package debs.cora.nytimesarticlaes.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import debs.cora.nytimesarticlaes.R
import debs.cora.nytimesarticlaes.utils.loadImage
import kotlinx.android.synthetic.main.single_row_article.view.*


class ArticlesRecyclerViewAdapter(
    private var values: List<model.Result>
) :
    RecyclerView.Adapter<ArticlesRecyclerViewAdapter.ViewHolder>() {

    private var onImageClicked: ((result: model.Result) -> Unit)? = null
    private var onItemClicked: ((result: model.Result) -> Unit)? = null

    fun setOnImageClicked(onImageClicked: (result: model.Result) -> Unit) {
        this.onImageClicked = onImageClicked
    }

    fun setOnItemClicked(onItemClicked: (result: model.Result) -> Unit) {
        this.onItemClicked = onItemClicked
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.bind(item)

    }

    fun setData(values: List<model.Result>) {
        this.values = values
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.single_row_article, parent, false)
        return ViewHolder(view)
    }


    override fun getItemCount() = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
       
        fun bind(item: model.Result) {

            itemView.setOnClickListener {
                onItemClicked?.invoke(item)
            }

            itemView.goToUrlImageButton.setOnClickListener {
                onImageClicked?.invoke(item)
            }
            itemView.singleItemTitleTextView.text = item.title
            itemView.singleItemSubTitleTextView.text = item.abstract
            itemView.singleItemDateTextView.text = item.publishedDate
            itemView.singleItemByTextView.text = item.source
            //getting the first image in the article and load it to the image view 
            item.media?.get(0)?.mediaMetadata?.get(0)?.url.let {itemView.singleItemImageView.loadImage(it.toString())}
            Log.e("s",""+ item.media?.get(0)?.mediaMetadata?.get(0).toString())
        }

    }
}