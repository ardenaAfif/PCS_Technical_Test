package id.pcs.pcstechnicaltest.presentation.ui.detail

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import id.pcs.pcstechnicaltest.R
import id.pcs.pcstechnicaltest.data.remote.ApiResponse
import id.pcs.pcstechnicaltest.databinding.ActivityDetailBinding
import id.pcs.pcstechnicaltest.utils.FormatHelper.setImageFromUrl
import id.pcs.pcstechnicaltest.utils.FormatHelper.splitName

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val user = intent.getParcelableExtra<ApiResponse>(EXTRA_DETAIL)
        user?.let {
            binding.apply {
                ivProfile.setImageFromUrl(this@DetailActivity, it.avatar.toString())

                // Pemisahan nama
                val (firstName, lastName) = splitName(it.name ?: "")
                tvFirstName.text = getString(R.string.name_format, firstName)
                tvLastName.text = getString(R.string.name_format, lastName)

                // Penggabungan alamat
                val address = getString(
                    R.string.address_format,
                    it.addressNo ?: "",
                    it.street ?: "",
                    it.city ?: "",
                    it.zipCode ?: "",
                    it.country ?: ""
                )
                tvAddress.text = address
            }
        }

        toolbarSetup()
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