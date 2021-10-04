package com.ethanhua.skeleton

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.bumptech.glide.Glide
import com.ethanhua.skeleton.data.Animal
import com.ethanhua.skeleton.data.animalList
import com.ethanhua.skeleton.databinding.ActivityAnimalDetailBinding

class AnimalDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAnimalDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimalDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val skeleton = Skeleton.bind(binding.root)
            .load(R.layout.animal_detail_skeleton)
            .build()

        skeleton.show()

        var currentAnimalId: Int? = null
        val bundle: Bundle? = intent.extras
        if (bundle != null) {
            currentAnimalId = bundle.getInt(ANIMAL_ID)
        }

        val animal = currentAnimalId?.let { getAnimalForId(it) }

        if (animal != null){
            binding.tvAnimalName.text = animal.name
            binding.tvAnimalDescription.text = animal.description
            Glide.with(this)
                .load(animal.imageUrl)
                .into(binding.ivAnimal)
        }

        Handler().postDelayed(
            Runnable { skeleton.hide() },
            3000
        )
    }

    private fun getAnimalForId(id: Int): Animal? {
        return animalList().find { it.id == id }
    }
}