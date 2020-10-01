package com.dscepointblank.pointblank.ui.fragments

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ShareCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dscepointblank.pointblank.R
import com.dscepointblank.pointblank.adapters.EventsAdapter
import com.dscepointblank.pointblank.models.FormattedEvents
import com.dscepointblank.pointblank.models.UpdateModel
import com.dscepointblank.pointblank.notifications.PushNotification
import com.dscepointblank.pointblank.utilityClasses.DownloadController
import com.dscepointblank.pointblank.utilityClasses.InjectorUtils
import com.dscepointblank.pointblank.utilityClasses.Resource
import com.dscepointblank.pointblank.utilityClasses.RetrofitInstance
import com.dscepointblank.pointblank.viewmodels.fragViewModels.HomeScreenFragViewModel
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_home_screen.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class HomeScreenFragment : Fragment(), EventsAdapter.EventsAdapterListener {

    private lateinit var downloadController: DownloadController
    private lateinit var viewModel: HomeScreenFragViewModel
    private lateinit var eventsAdapter: EventsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().window.statusBarColor = Color.parseColor("#212121")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home_screen, container, false)
    }

    @ExperimentalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = InjectorUtils.provideHomeScreenViewModelFactory()
        viewModel = ViewModelProvider(this, factory).get(HomeScreenFragViewModel::class.java)
        setUpRecyclerView()


        fragHomeScreen_swipe.setOnRefreshListener { viewModel.refresh() }
        fragHomeScreen_swipe.setColorSchemeColors(Color.GREEN)

        viewModel.allEvents.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Success -> {
                    fragHomeScreen_swipe.isRefreshing = false
                    eventsAdapter.differ.submitList(it.data)
                    pb_fragHomeScreen.visibility =View.GONE
                }
            }
        })

        viewModel.allFlowEvents.observe(viewLifecycleOwner,{
            Log.d("ZZZZ","From FLOW "+it.data.toString())
        })
    }

    private fun setUpRecyclerView() {
        eventsAdapter = EventsAdapter(this)
        rv_HSFrag.apply {
            adapter = eventsAdapter
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireActivity())
        }
    }

    private fun sendNotification(notification: PushNotification) =
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response = RetrofitInstance.api.postNotification(notification)
                if (response.isSuccessful) {
                    Log.d("DDDD", "Response is ${Gson().toJson(response.toString())}")
                }

            } catch (e: Exception) {
                Log.d("DDDD", e.localizedMessage!!)
            }
        }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        downloadController.onPermissionResult(requestCode, permissions, grantResults)
    }

    private fun checkForUpdates() =
        try {
            downloadController =
                DownloadController(
                    requireContext(),
                    UpdateModel(1, "d")
                )
            downloadController.beginDownloadProcess()
        } catch (e: Exception) {
            Log.d("DDDD", e.localizedMessage!!)
        }

    override fun onEventShared(formattedEvents: FormattedEvents) {
        val shareableContent = viewModel.getShareableEventData(formattedEvents)
        ShareCompat.IntentBuilder.from(requireActivity())
            .setText(shareableContent).setType("text/plain")
            .setChooserTitle("Event Details")
            .startChooser()
    }
}

//        viewModel.getContests()

//        viewModel.clistContests.observe(viewLifecycleOwner, Observer { response ->
//            when (response) {
//                is Resource.Success -> {
//                    Log.d("SSSS", response.data.toString())
//                    eventsAdapter.differ.submitList(response.data)
//                }
//                is Resource.Error ->
//                    Log.d("SSSS", "error" + response.message.toString())
//            }
//
//        })

// Inflate the layout for this fragment


//        view.btn_sendNotificationHomeScreen.setOnClickListener {
//            if (view.tv_notificationTitleHomeScreen.text.toString().isNotEmpty() && view.tv_notificationDesHomeScreen.text.toString().isNotEmpty()) {
//
//                PushNotification(
//                    NotificationData(
//                        view.tv_notificationTitleHomeScreen.text.toString(),
//                        view.tv_notificationDesHomeScreen.text.toString()
//                    ), TOPIC
//                )
//                    .also { sendNotification(it) }
//            }
//        }


//        view.btn_updateAppHomeScreen.setOnClickListener {
//            checkForUpdates()
//        }