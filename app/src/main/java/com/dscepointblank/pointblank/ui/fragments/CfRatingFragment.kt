package com.dscepointblank.pointblank.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.dscepointblank.pointblank.R
import com.dscepointblank.pointblank.factories.CfRatingFragFactory
import com.dscepointblank.pointblank.repositories.CfRatingFragRepository
import com.dscepointblank.pointblank.utilityClasses.Resource
import com.dscepointblank.pointblank.viewmodels.fragViewModels.CfRatingFragViewModel
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import kotlinx.android.synthetic.main.fragment_cf_rating.*

class CfRatingFragment : Fragment(R.layout.fragment_cf_rating) {

    lateinit var viewModel  : CfRatingFragViewModel

    lateinit var entryList : ArrayList<Entry>
    lateinit var dataSetOne : LineDataSet
    lateinit var dataA : LineData
    lateinit var dataSets : ArrayList<LineDataSet>

    private val TAG = "CfRatingFragment"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate: Creating all vals")

        val cfRatingRepository = CfRatingFragRepository()
        val cfFactory = CfRatingFragFactory(cfRatingRepository)
        viewModel = ViewModelProvider(this,cfFactory).get(CfRatingFragViewModel::class.java)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d(TAG, "onViewCreated: In here")
        viewModel.getCfUserRatingChange("tourist")
        entryList = ArrayList()

        viewModel.cfRatingLiveData.observe(viewLifecycleOwner, Observer {
            Log.d(TAG, "onViewCreated: Observing livedata")
            when (it) {
                is Resource.Success -> {
                    Log.d(TAG, "onViewCreated: Success , msg is ${it.message}")
                    hideProgressBar()
                    it.data?.let { response ->
                        var count = 0
                        Log.d(TAG, "onViewCreated: Respose $response")
                        for (result in response.result) {
                            entryList.add(Entry(count.toFloat(), result.newRating.toFloat()))
                            count++
                        }

                        plotChart()
                    }
                }

                is Resource.Error -> {
                    Log.d(TAG, "onViewCreated: ERROR , msg is ${it.message}")

                    hideProgressBar()
                    it.message?.let {
                        Log.d(TAG, "I am in error: $it")
                    }
                }

                is Resource.Loading -> {
                    Log.d(TAG, "onViewCreated: Loading , msg is ${it.message}")

                    showProgressBar()
                }

            }
        })


    }

    private fun plotChart() {

        dataSetOne = LineDataSet(entryList,"Set 1")
        dataSets = ArrayList()
        dataSets.add(dataSetOne)
        dataA = LineData(dataSets as List<ILineDataSet>?)

        cfRatingLineChart.apply {

            isDragEnabled = true
            setScaleEnabled(true)
//            notifyDataSetChanged()
            data = dataA
            visibility = View.VISIBLE
        }

    }

    private fun showProgressBar(){
        progressBarCfRatingFragment.visibility = View.VISIBLE
    }

    private fun hideProgressBar(){
        progressBarCfRatingFragment.visibility = View.INVISIBLE
    }

}