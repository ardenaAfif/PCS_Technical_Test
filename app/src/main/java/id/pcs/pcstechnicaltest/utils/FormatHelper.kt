package id.pcs.pcstechnicaltest.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import id.pcs.pcstechnicaltest.R
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

object FormatHelper {

    fun formatDate(dateString: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
        val outputFormat = SimpleDateFormat("dd MMMM yyyy", Locale("id", "ID"))
        outputFormat.timeZone = TimeZone.getTimeZone("Asia/Jakarta")

        return try {
            val date = inputFormat.parse(dateString)
            outputFormat.format(date!!)
        } catch (e: ParseException) {
            e.printStackTrace()
            dateString // Return original date string if parsing fails
        }
    }

    fun ImageView.setImageFromUrl(context: Context, url: String) {
        Glide.with(context)
            .load(url)
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(this)
    }

}