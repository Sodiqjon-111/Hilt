package uz.digitalone.hilt

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_first.*
import logd
import uz.digitalone.hilt.adapter.UserAdapter
import uz.digitalone.hilt.core.Resource
import uz.digitalone.hilt.presentation.MainViweModel
import uz.digitalone.hilt.response.UserModel

@AndroidEntryPoint
class FirstFragment : Fragment() {
    private val viewModel by activityViewModels<MainViweModel>()
    private lateinit var listAdapter: UserAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        logd("first fragment")
        listAdapter = UserAdapter()
        recycler?.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = listAdapter
            setHasFixedSize(true)
        }
//        recycler.layoutManager = LinearLayoutManager(requireContext())
//        listAdapter = UserAdapter(arrayListOf())
//        recycler.adapter=listAdapter


        viewModel.userlist.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {
                    Log.d("---------------", "load")
                }
                is Resource.Success -> {
                    Log.d("---------------", "onViewCreated: ${it.data.joinToString()}")
                    renderList(it.data)

                }
                is Resource.Failure -> {
                    Log.d("---------------", "load")
                }
            }
        }
    }

    private fun renderList(users: List<UserModel>) {
        listAdapter.addData(users)
    }


}

