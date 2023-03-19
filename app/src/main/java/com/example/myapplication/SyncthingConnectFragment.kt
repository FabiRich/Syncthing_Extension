package com.example.myapplication

import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class SyncthingConnectFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val buttonSynchronisation = getView()?.findViewById<Button>(R.id.startSynchronisation)

        buttonSynchronisation?.setOnClickListener()
        {

//            val i = Intent()
//            i.action = "com.nutomic.syncthingandroid.action.START"
            val intent = Intent("com.nutomic.syncthingandroid.action.START")
            intent.setPackage("com.nutomic.syncthingandroid")
            try {
                activity?.baseContext?.sendBroadcast(intent)
                startActivity(intent)
                Toast.makeText(
                    this@SyncthingConnectFragment.context,
                    "Hat funktioniert",
                    Toast.LENGTH_LONG
                ).show()
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(
                    this@SyncthingConnectFragment.context,
                    R.string.message,
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        var buttonAddDevice =
            getView()?.findViewById<FloatingActionButton>(R.id.floatingActionButton)
        buttonAddDevice?.setOnClickListener {
            val launchSyncthing =
                activity?.packageManager?.getLaunchIntentForPackage("com.nutomic.syncthingandroid")
            startActivity(launchSyncthing)
        }
    }

    fun stopSyncthing(){
        val i = Intent()
        i.action = "com.nutomic.syncthingandroid.action.STOPP"
        i.setPackage("com.nutomic.syncthingandroid")
        context?.sendBroadcast(i)
    }

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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_syncthing_connect, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SyncthingConnectFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SyncthingConnectFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}