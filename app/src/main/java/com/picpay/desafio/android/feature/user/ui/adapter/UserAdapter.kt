package com.picpay.desafio.android.feature.user.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.R
import com.picpay.desafio.android.feature.user.domain.model.User
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item_user.view.*

class UserAdapter(private val mUserList: MutableList<User>): RecyclerView.Adapter<UserAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view  = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.list_item_user, parent, false)

        return ItemViewHolder(view)
    }

    override fun getItemCount(): Int = mUserList.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = mUserList[position]
        holder.bind(item)
    }

    fun updateItems(newItems: List<User>) {
        mUserList.addAll(newItems)
        notifyDataSetChanged()
    }

    inner class ItemViewHolder(view: View): RecyclerView.ViewHolder(view) {
        fun bind(mUserList: User?){
            itemView.name.text = mUserList?.name ?: "Nome não informado!"
            itemView.username.text = mUserList?.username ?: "Username não informado!"
            itemView.progressBar.visibility = View.VISIBLE
            Picasso.get()
                .load(mUserList?.img)
                .error(R.drawable.ic_round_account_circle)
                .into(itemView.picture, object : Callback {
                    override fun onSuccess() {
                        itemView.progressBar.visibility = View.GONE
                    }

                    override fun onError(e: Exception?) {
                        itemView.progressBar.visibility = View.GONE
                    }
                })
        }
    }
}