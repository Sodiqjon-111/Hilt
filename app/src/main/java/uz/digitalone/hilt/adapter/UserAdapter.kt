package uz.digitalone.hilt.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item.view.*
import uz.digitalone.hilt.R
import uz.digitalone.hilt.response.UserModel

class UserAdapter() :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
    private  var  items: ArrayList<UserModel> = arrayListOf()
    class UserViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = UserViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
    )

    override fun onBindViewHolder(holder: UserAdapter.UserViewHolder, position: Int) {
        val item = items[position]
        holder.itemView.setOnClickListener {

        }
        holder.itemView.userId.text = item.userId.toString()
        holder.itemView.idName.text = item.id.toString()
        holder.itemView.title.text = item.title
        holder.itemView.body.text = item.body
    }

    override fun getItemCount(): Int {
        return items.count()
    }
    fun addData(list: List<UserModel>){
        items.addAll(list)
        notifyDataSetChanged()
    }
}