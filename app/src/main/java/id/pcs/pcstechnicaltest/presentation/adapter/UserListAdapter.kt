package id.pcs.pcstechnicaltest.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import id.pcs.pcstechnicaltest.data.UserRepository
import id.pcs.pcstechnicaltest.data.remote.ApiResponse
import id.pcs.pcstechnicaltest.databinding.ItemUserBinding
import id.pcs.pcstechnicaltest.utils.FormatHelper
import id.pcs.pcstechnicaltest.utils.FormatHelper.setImageFromUrl

class UserListAdapter(private val context: Context) :
    RecyclerView.Adapter<UserListAdapter.UserViewHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<ApiResponse>() {
        override fun areItemsTheSame(oldItem: ApiResponse, newItem: ApiResponse): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ApiResponse, newItem: ApiResponse): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, diffCallback)

    inner class UserViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: ApiResponse) {
            binding.apply {
                tvName.text = user.name
                tvDate.text = FormatHelper.formatDate(user.createdAt ?: "")
                ivProfile.setImageFromUrl(context, user.avatar.toString())
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserListAdapter.UserViewHolder {
        return UserViewHolder(
            ItemUserBinding.inflate(
                LayoutInflater.from(context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: UserListAdapter.UserViewHolder, position: Int) {
        val userList = differ.currentList[position]
        holder.bind(userList)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}