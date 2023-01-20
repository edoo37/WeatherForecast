package com.yasinsenel.weatherforecast.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.orhanobut.hawk.Hawk
import com.yasinsenel.weatherforecast.R
import com.yasinsenel.weatherforecast.databinding.FragmentSearchBinding
import kotlin.random.Random


class SearchFragment : Fragment() {
    private lateinit var binding : FragmentSearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Hawk.init(requireContext()).build()


        val list : ArrayList<Int> = arrayListOf()
        list.add(R.drawable.ic_2girls)
        list.add(R.drawable.ic_female_cyclist)
        list.add(R.drawable.ic_female_rain)
        list.add(R.drawable.ic_husband_wife)
        list.add(R.drawable.ic_men_summer)
        list.add(R.drawable.ic_person_umbrella)
        list.add(R.drawable.ic_umbrella_girl)

        val calculate = list.get(Random.nextInt(list.size))
        binding.imageView2.setImageResource(calculate)


        binding.apply {
            button.setOnClickListener {
                //val bundle = Bundle()
                val cityName = editTextTextPersonName.text.toString()
                Hawk.put("City",cityName)
                //bundle.putString("City",text)
                Navigation.findNavController(view).navigate(R.id.action_searchFragment_to_mainScreenFragment)
            }
        }
    }

}