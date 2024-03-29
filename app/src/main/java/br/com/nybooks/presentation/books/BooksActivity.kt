package br.com.nybooks.presentation.books

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.nybooks.R
import kotlinx.android.synthetic.main.activity_books.rcvBooks
import kotlinx.android.synthetic.main.activity_books.toolbarMain

class BooksActivity : AppCompatActivity() {

    lateinit var viewModel:BooksViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_books)

        //Init
        viewModel = ViewModelProviders.of(this).get(BooksViewModel::class.java)
        viewModel.getBooks()
        setObservable(viewModel)
        initToolBar()



    }

    private fun setObservable(viewModel: BooksViewModel) {
        viewModel.booksLiveData.observe(this, Observer {
            it?.let { books ->
                with(rcvBooks) {
                    layoutManager =
                        LinearLayoutManager(this@BooksActivity, RecyclerView.VERTICAL, false)
                    adapter = BooksAdapter(books)
                    setHasFixedSize(true)
                }

            }
        })
    }

    private fun initToolBar(){
        toolbarMain.title = getString(R.string.books_title)
        setSupportActionBar(toolbarMain)
    }
}
