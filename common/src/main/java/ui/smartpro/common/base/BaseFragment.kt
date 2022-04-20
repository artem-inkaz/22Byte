package ui.smartpro.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.launch

abstract class BaseFragment<Binding : ViewBinding> : Fragment() {

    val TAG = javaClass.simpleName

    protected abstract val layoutId: Int

    protected lateinit var binding: Binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
        initViewModel()
    }

    protected open fun initViewModel() {}
    protected open fun initViews(view: View) {}

    protected fun doInScope(
        state: Lifecycle.State = Lifecycle.State.STARTED,
        action: suspend () -> Unit
    ) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(state) {
                action()
            }
        }
    }

    protected fun doInScopeResume(
        state: Lifecycle.State = Lifecycle.State.RESUMED,
        action: suspend () -> Unit
    ) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(state) {
                action()
            }
        }
    }
}