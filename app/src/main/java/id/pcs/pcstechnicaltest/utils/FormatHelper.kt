package id.pcs.pcstechnicaltest.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
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
            dateString // if parsing fails -> return original date string
        }
    }

    fun ImageView.setImageFromUrl(context: Context, url: String) {
        Glide.with(context)
            .load(url)
            .placeholder(R.drawable.ic_launcher_foreground)
            .transform(CircleCrop())
            .into(this)
    }

    fun splitName(fullName: String): Pair<String, String> {
        val parts = fullName.split(" ", limit = 2)
        val firstName = parts.getOrNull(0) ?: ""
        val lastName = parts.getOrNull(1) ?: ""
        return firstName to lastName
    }

}