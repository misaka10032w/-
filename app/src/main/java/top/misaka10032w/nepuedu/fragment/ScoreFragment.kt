package top.misaka10032w.nepuedu.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import top.misaka10032w.nepuedu.DyjwApplication
import top.misaka10032w.nepuedu.R
import top.misaka10032w.nepuedu.databinding.FragmentScoreBinding
import top.misaka10032w.nepuedu.logic.ScoreAdapter
import top.misaka10032w.nepuedu.logic.Util.BroccoliManager
import top.misaka10032w.nepuedu.logic.model.scoreitems
import kotlin.random.Random

class ScoreFragment : Fragment() {
    private var _binding: FragmentScoreBinding? = null
    private val binding get() = _binding!!
    val scoreList = ArrayList<scoreitems>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentScoreBinding.inflate(inflater, container, false)
        binding.score.layoutManager = LinearLayoutManager(DyjwApplication.context)
        val adapter = ScoreAdapter(DyjwApplication.context, scoreList)
        binding.score.adapter = adapter
repeat(10){scoreList.add(scoreitems("0","","","",0XFF4CAF50.toInt()))}

        return binding.root
    }

    @SuppressLint("ResourceType")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val spinner: Spinner = binding.spinner
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            DyjwApplication.context,
            R.array.list,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter

        }
        binding.button2.setOnClickListener{
            scoreList.clear()
            repeat(20) {
                val a = (0..100).random().toString()
                scoreList.add(scoreitems("nmememem", "2010-2011", "3", a, 0XFF4CAF50.toInt()))
            }
           val adapter = ScoreAdapter(DyjwApplication.context, scoreList)
            binding.score.adapter = adapter

            Log.v("123",scoreList[1].coursename)

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

