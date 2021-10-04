package com.ethanhua.skeleton.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ethanhua.skeleton.data.Animal
import com.ethanhua.skeleton.databinding.SampleRowItemBinding

class SampleAdapter(private val dataSet: List<Animal>, private val onClick: (Animal) -> Unit) :
    RecyclerView.Adapter<SampleAdapter.SampleViewHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SampleViewHolder {
        val binding =
            SampleRowItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        context = parent.context

        return SampleViewHolder(context, binding, onClick)
    }

    override fun onBindViewHolder(holder: SampleViewHolder, position: Int) {
        holder.bind(dataSet[position])
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    class SampleViewHolder(
        private val context: Context,
        private val binding: SampleRowItemBinding,
        val onClick: (Animal) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {

        private var currentAnimal: Animal? = null

        init {
            binding.root.setOnClickListener {
                currentAnimal?.let {
                    onClick(it)
                }
            }
        }

        fun bind(animal: Animal) {
            currentAnimal = animal

            binding.tvAnimalName.text = animal.name
            binding.tvAnimalDescription.text = animal.description
            Glide.with(context)
                .load(animal.imageUrl)
                .circleCrop()
                .into(binding.ivAnimal)
        }

    }
}