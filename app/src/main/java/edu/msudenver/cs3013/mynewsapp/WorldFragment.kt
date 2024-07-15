package edu.msudenver.cs3013.mynewsapp

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import edu.msudenver.cs3013.mynewsapp.model.Article
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.time.LocalDate
import java.time.format.DateTimeFormatter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [WorldFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WorldFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var articles: List<Article>
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_world, container, false)
        recyclerView = view.findViewById(R.id.worldRecyclerView)
        (activity as AppCompatActivity).supportActionBar?.title = "World News"
        val retrofit = Retrofit.Builder()
            .baseUrl("https://newsapi.org/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        val newsApiService = retrofit.create(NewsApiService::class.java)
        lifecycleScope.launch {
            val dateFrom = LocalDate.now().minusDays(7)
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            val formattedDateFrom = dateFrom.format(formatter)
            val response = newsApiService.getEverything("news", ApiKey.KEY, 1, formattedDateFrom)
            Log.d("WorldFragment", "Fetched ${response.articles.size} articles")
            articles = response.articles
                .filter { article ->
                    val passesFilter = !article.title.isNullOrEmpty() &&
                            !article.description.isNullOrEmpty() &&
                            !article.urlToImage.isNullOrEmpty() &&
                            !article.title.contains("removed", ignoreCase = true) &&
                            !article.title.contains("Morning news brief", ignoreCase = true) &&
                            !article.title.contains("Watch", ignoreCase = true)
                    if (!passesFilter) {
                        Log.d("WorldFragment", "Filtered out article with title '${article.title}'")
                    }
                    passesFilter
                }
                .take(20)
            Log.d("WorldFragment", "Displaying ${articles.size} articles")

            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = ArticleAdapter(articles)
        }

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment WorldFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            WorldFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}