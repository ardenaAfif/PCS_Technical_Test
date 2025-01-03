package id.pcs.pcstechnicaltest.presentation.ui.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import id.pcs.pcstechnicaltest.R
import id.pcs.pcstechnicaltest.databinding.ActivityHomeBinding
import id.pcs.pcstechnicaltest.presentation.adapter.UserListAdapter
import id.pcs.pcstechnicaltest.presentation.viewmodel.HomeViewModel

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var userAdapter: UserListAdapter
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setRecyclerView()

        viewModel.loading.observe(this) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        viewModel.error.observe(this) { errorMessage ->
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
        }

        viewModel.userData.observe(this) { data ->
            userAdapter.differ.submitList(data)
        }
    }

    private fun setRecyclerView() {
        userAdapter = UserListAdapter(this)
        binding.rvUser.apply {
            adapter = userAdapter
            layoutManager = LinearLayoutManager(this@HomeActivity)
        }
    }
}