package kg.ruslan.photosapp.ui

import kg.ruslan.core.base.BaseFragment
import kg.ruslan.core.sharedpreference.GreetingFragmentOpened
import kg.ruslan.photosapp.databinding.FragmentToGoBinding
import org.koin.android.ext.android.inject

/**
 * This fragment [FragmentToGo] using for resolve task when you do not know have [GreetingFragment] opened or not
 *
 * if greeting fragment have opened so it'll open [HomeFragment] else [GreetingFragment]
 */
class FragmentToGo : BaseFragment<FragmentToGoBinding>(FragmentToGoBinding::inflate) {

    private val greetingFragmentOpened: GreetingFragmentOpened by inject()

    override fun initialize() {
        if (greetingFragmentOpened.haveGreetingFragmentOpened()) openHomeScreen()
        else openGreetingScreen()
    }

    private fun openGreetingScreen() {
        TODO("Not yet implemented")
    }

    private fun openHomeScreen() {
        TODO("Not yet implemented")
    }

}