package kg.ruslan.core.base

import android.Manifest
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch


typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

open class BaseFragment<Binding : ViewBinding>(
    private val inflate: Inflate<Binding>
) : Fragment() {

    private var _binding: Binding? = null
    val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = inflate.invoke(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
    }

    protected open fun initialize() {}


    @OptIn(InternalCoroutinesApi::class)
    protected inline fun <T, State> StateFlow<State>.partialListener(crossinline block: (T) -> Unit, crossinline transform: suspend (value: State) -> T) {
        lifecycleScope.launch {
            map(transform)
                .distinctUntilChanged()
                .collect {
                    block(it)
                }
        }
    }

    @OptIn(InternalCoroutinesApi::class)
    protected suspend fun <T, State> partialListener(state: StateFlow<State>, variable: T, block: (T) -> Unit) {
        state.map { variable }
            .distinctUntilChanged()
            .collect {
                block(it)
            }
    }

    /**
     * Request some permission
     *
     * @param permission is some permission form [Manifest.permission] group
     * @param isGranted is higher-order function which is callback for permission
     */
    protected fun requestPermission(
        permissions: Array<String>,
        result: (isGranted: Boolean) -> Unit = {}
    ) {
        val request = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { perRes ->
            var isGranted = true
            permissions.forEach {
                isGranted = perRes[it] ?: false
            }
            result(isGranted)
        }
        request.launch(permissions)
    }

}