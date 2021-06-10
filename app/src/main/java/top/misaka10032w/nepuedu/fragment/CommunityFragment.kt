package top.misaka10032w.nepuedu.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.scwang.smart.refresh.header.ClassicsHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnRefreshListener
import top.misaka10032w.nepuedu.DyjwApplication
import top.misaka10032w.nepuedu.R
import top.misaka10032w.nepuedu.databinding.FragmentCommunityBinding


class CommunityFragment : Fragment() {
    private var _binding: FragmentCommunityBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.refreshLayout.setOnRefreshListener(object : OnRefreshListener {
            override fun onRefresh(refreshlayout: RefreshLayout) {
                Toast.makeText(DyjwApplication.context, "11111", Toast.LENGTH_SHORT).show()
                refreshlayout.finishRefresh(2000 /*,false*/) //传入false表示刷新失败
            }
        })
//        binding.refreshLayout.setOnLoadMoreListener { refreshlayout ->
//            refreshlayout.finishLoadMore(2000 /*,false*/) //传入false表示加载失败
//        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): SmartRefreshLayout {
        _binding = FragmentCommunityBinding.inflate(inflater, container, false)
        return binding.root

    }

}