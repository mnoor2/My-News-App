package edu.msudenver.cs3013.mynewsapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class ArticleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        val articleImage = findViewById<ImageView>(R.id.article_image)
        val articleTitle = findViewById<TextView>(R.id.article_title)
        val articleDescription = findViewById<TextView>(R.id.article_description)
        val articleContent = findViewById<TextView>(R.id.article_content)
        val articleLink = findViewById<Button>(R.id.article_link)

        val intent = intent
        val image = intent.getStringExtra("image")
        val title = intent.getStringExtra("title")
        val description = intent.getStringExtra("description")
        val content = intent.getStringExtra("content")
        val cleanedContent = content?.replace("\\[\\+\\d+ chars\\]".toRegex(), "")
        val url = intent.getStringExtra("url")

        Glide.with(this).load(image).into(articleImage)
        articleTitle.text = title
        articleDescription.text = description
        articleContent.text = cleanedContent

        articleLink.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(browserIntent)
        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here
        when (item.itemId) {
            android.R.id.home -> {
                // Respond to the action bar's Up/Home button
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}