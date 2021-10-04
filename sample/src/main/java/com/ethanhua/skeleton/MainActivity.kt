package com.ethanhua.skeleton

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.ethanhua.skeleton.adapter.SampleAdapter
import com.ethanhua.skeleton.data.Animal
import com.ethanhua.skeleton.data.animalList
import com.ethanhua.skeleton.databinding.ActivityMainBinding

const val ANIMAL_ID = "animal_id"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var sampleAdapter: SampleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sampleAdapter = SampleAdapter(animalList()) { animal -> adapterOnClick(animal) }

        binding.rvSample.adapter = sampleAdapter
        binding.rvSample.layoutManager = LinearLayoutManager(this)

        val skeleton = Skeleton.bind(binding.rvSample)
            .adapter(sampleAdapter)
            .load(R.layout.sample_row_skeleton)
            .build()

        skeleton.show()

        Handler().postDelayed(
            Runnable { skeleton.hide() },
            3000
        )
    }

    private fun adapterOnClick(animal: Animal) {
        val intent = Intent(this, AnimalDetailActivity::class.java)
        intent.putExtra(ANIMAL_ID, animal.id)
        startActivity(intent)
    }
}