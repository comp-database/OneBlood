package com.vpppcoe.oneblood.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.vpppcoe.oneblood.MainActivity
import com.vpppcoe.oneblood.R
import com.vpppcoe.oneblood.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {


    // This property is only valid between onCreateView and
    // onDestroyView.

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // Add user id Here
        val userID : String = "None"
        view.findViewById<TextView>(R.id.USERID).text = userID

        val BgroupImg = view.findViewById<ImageView>(R.id.BLOOD_IMG)
        val Bgroup : String  = "B+ve"// firebase data retrieve
        val ImageResource = when(Bgroup){
            "A+ve" -> R.drawable.a_p
            "B+ve" ->R.drawable.b_p
            "O+ve" ->R.drawable.o_p
            "AB+ve" ->R.drawable.ab_p
            "B-ve" ->R.drawable.b_n
            "O-ve" ->R.drawable.o_n
            "AB-ve" ->R.drawable.ab_n
            else -> R.drawable.none
        }
        BgroupImg.setImageResource(ImageResource)
        BgroupImg.contentDescription = userID
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as MainActivity).supportActionBar?.title = "Atharv"
    }
}