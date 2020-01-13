package fragments

import model.Result
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import debs.cora.nytimesarticlaes.R
import kotlinx.android.synthetic.main.item_detail.view.*

class ItemDetailFragment : Fragment() {

    private var item: Result? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            item = it.getSerializable("Result") as Result?
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.item_detail, container, false)

        // Show the article content as text in a TextView.
        item?.let {
            rootView.item_detail.text ="${it.abstract}" +
                    "\nViews: ${it.views}" +
                    "\nBy: ${it.source}" +
                    "\nDate: ${it.publishedDate}"
        }

        return rootView
    }
}
