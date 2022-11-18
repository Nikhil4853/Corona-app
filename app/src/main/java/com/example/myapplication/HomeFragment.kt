package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.myapplication.adapter.adapter
import com.example.corona.stateData


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class HomeFragment : Fragment() {

   lateinit var Aadapter:adapter

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root=inflater.inflate(R.layout.fragment_home, container, false)
        var RecyclerView=root.findViewById(R.id.RecyclerView) as RecyclerView
        RecyclerView.layoutManager = LinearLayoutManager(activity)

        Aadapter = adapter()
        RecyclerView.adapter = Aadapter



        val queue = Volley.newRequestQueue(context)
        val url = "https://api.rootnet.in/covid19-in/stats/latest"

// Request a string response from the provided URL.
        val JsonRequest = JsonObjectRequest(
            Request.Method.GET,
            url,
            null,
            Response.Listener { respons ->
                val dataObject = respons.getJSONObject("data")
                val reoginalArray = dataObject.getJSONArray("regional")
                val dataList = ArrayList<stateData>()
                for (i in 0 until reoginalArray.length()) {
                    val stateJsonObject = reoginalArray.getJSONObject(i)
                    val stateData = stateData(
                        stateJsonObject.getString("loc"),
                        stateJsonObject.getInt("confirmedCasesIndian"),
                        stateJsonObject.getInt("discharged"),
                        stateJsonObject.getInt("deaths")
                    )
                    dataList.add(stateData)
                }
                Aadapter.updateState(dataList)
            },
            Response.ErrorListener {

            }

        )

// Add the request to the RequestQueue.
        queue.add(JsonRequest)
        return root
    }
    private fun fetchData() {

    }



}