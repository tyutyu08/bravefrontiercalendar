package eijenson.bravefrontiercalendar.view.fragment

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import eijenson.bravefrontiercalendar.DevUtils
import eijenson.bravefrontiercalendar.R
import kotlinx.android.synthetic.main.fragment_develop.*

class DevelopFragment : Fragment() {

    companion object {
        fun newInstance(): DevelopFragment {
            return DevelopFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_develop, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clear.setOnClickListener {
            DevUtils.clear()
        }
        dev.setOnClickListener {
            DevUtils.dev()
        }
    }
}