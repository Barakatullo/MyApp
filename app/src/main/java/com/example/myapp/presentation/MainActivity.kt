package com.example.myapp.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.RecyclerView
import com.example.myapp.R
import com.example.myapp.domain.entity.CurrencyState
import com.example.myapp.presentation.adapter.CurrenctAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    private val component by lazy {
        (application as CurrencyApp).component
    }

    @Inject
    lateinit var viewmodelFactory: ViewModelFactory
    private val viewModel by lazy {
        ViewModelProvider(this, viewmodelFactory)[MainViewModel::class.java]
    }

    private lateinit var adapter: CurrenctAdapter
    private lateinit var prBar: ProgressBar
    private lateinit var tV: TextView
    private lateinit var rc: RecyclerView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tV = findViewById(R.id.ListUp)
        prBar = findViewById(R.id.PrBar)
        rc = findViewById(R.id.rvPriceList)
        adapter = CurrenctAdapter(this)
        rc.adapter = adapter
        initViewModel()
    }

    private fun initViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.timeState.collect {
                    tV.text = it
                }
            }

        }
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.state
                    .onEach {
                        when (it) {
                            is CurrencyState.Success -> {
                                adapter.submitList(it.list)
                                prBar.visibility = View.INVISIBLE
                            }

                            else -> {
                                prBar.visibility = View.VISIBLE
                            }
                        }
                    }
                    .collect {
                    }
            }

        }
    }
}


