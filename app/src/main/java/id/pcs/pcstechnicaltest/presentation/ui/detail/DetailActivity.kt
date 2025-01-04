package id.pcs.pcstechnicaltest.presentation.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import id.pcs.pcstechnicaltest.R
import id.pcs.data.remote.ApiResponse
import id.pcs.pcstechnicaltest.databinding.ActivityDetailBinding
import id.pcs.pcstechnicaltest.utils.FormatHelper.setImageFromUrl
import id.pcs.pcstechnicaltest.utils.FormatHelper.splitName

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val user = intent.getParcelableExtra<ApiResponse>(EXTRA_DETAIL)
        user?.let {
            setupUserDetails(it)
        }

        toolbarSetup()
    }

    private fun setupUserDetails(user: ApiResponse) {
        binding.apply {
            ivProfile.setImageFromUrl(this@DetailActivity, user.avatar.toString())

            // Pemisahan nama
            val (firstName, lastName) = splitName(user.name ?: "")
            tvFirstName.text = getString(R.string.name_format, firstName)
            tvLastName.text = getString(R.string.name_format, lastName)

            // Penggabungan alamat
            val address = getString(
                R.string.address_format,
                user.addressNo ?: "",
                user.street ?: "",
                user.city ?: "",
                user.zipCode ?: "",
                user.country ?: ""
            )
            tvAddress.text = address
        }
    }

    private fun toolbarSetup() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            setHomeAsUpIndicator(R.drawable.arrow_back)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    companion object {
        const val EXTRA_DETAIL = "extra_detail"
    }
}