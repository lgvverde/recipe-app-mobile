package dev.lucasvillaverde.recipeapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textview.MaterialTextView
import com.squareup.picasso.Picasso
import dev.lucasvillaverde.recipeapp.R
import dev.lucasvillaverde.recipeapp.data.local.entities.MealEntity
import kotlinx.android.synthetic.main.meal_list_item.view.*

class MealAdapter(private val mealDataset: List<MealEntity>) :
    RecyclerView.Adapter<MealAdapter.MealViewHolder>() {

    class MealViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.meal_list_item, parent, false)) {

        private var mealTitle: MaterialTextView? = null
        private var mealDescription: MaterialTextView? = null
        private var mealImage: ImageView? = null

        init {
            mealTitle = itemView.mealTitle
            mealDescription = itemView.mealDescription
            mealImage = itemView.mealImg
        }

        fun bind(mealEntity: MealEntity) {
            mealTitle?.text = mealEntity.name
            mealDescription?.text = mealEntity.category
            Picasso.get().load(mealEntity.thumb).into(mealImage)
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MealViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        val meal: MealEntity = mealDataset[position]
        holder.bind(meal)
    }

    override fun getItemCount(): Int = mealDataset.size

}