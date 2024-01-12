package com.example.advnative_waroengujang.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.advnative_waroengujang.R
import com.example.advnative_waroengujang.databinding.MenuListItemBinding
import com.example.advnative_waroengujang.model.Menu
import com.example.advnative_waroengujang.view.ListMenuFragment.Companion.positionSelected
import com.squareup.picasso.Picasso

class ListMenuAdapter(val menuList: ArrayList<Menu>, val tableNumber: Int) :
    RecyclerView.Adapter<ListMenuAdapter.MenuViewHolder>() {
    class MenuViewHolder(var view: MenuListItemBinding) : RecyclerView.ViewHolder(view.root) {
        fun bind(menu: Menu, tableNumber: Int){
            view.menu = menu

            Picasso.get().load(menu.photo).into(view.imgMenu)
//
//            view.lblMenuName.text = menu.name
//            view.lblMenuPrice.text = "IDR " + menu.price
            view.cardMenu.setOnClickListener {
                if (tableNumber == 0) {
                    Toast.makeText(itemView.context, "Please input Table Number first.", Toast.LENGTH_SHORT).show()
                } else {
                    // passing data dengan args untuk detail fragment
                    val action = ListMenuFragmentDirections.actionListMenuFragmentToDetailMenuFragment(
                        menuId = menu.id,
                        name = menu.name,
                        description = menu.description,
                        price = menu.price,
                        photo = menu.photo,
                    )
                    Navigation.findNavController(it).navigate(action)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = MenuListItemBinding.inflate(inflater, parent, false)
        return MenuViewHolder(view)
    }

    override fun getItemCount(): Int = menuList.size

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {

        val menu = menuList[position]
        holder.bind(menu, tableNumber)
    }

    fun updateListMenu(newMenuList: List<Menu>) {
        menuList.clear()
        menuList.addAll(newMenuList)
        notifyDataSetChanged()
    }
}