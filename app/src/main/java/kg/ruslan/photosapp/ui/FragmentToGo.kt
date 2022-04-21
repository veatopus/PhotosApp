package kg.ruslan.photosapp.ui

import androidx.navigation.fragment.findNavController
import kg.ruslan.core.base.BaseFragment
import kg.ruslan.core.sharedpreference.GreetingFragmentOpened
import kg.ruslan.photosapp.R
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
        if (!greetingFragmentOpened.haveGreetingFragmentOpened()) openHomeScreen()
        else openGreetingScreen()
    }

    private fun openGreetingScreen() {
        findNavController().navigate(R.id.action_fragmentToGo_to_greeting)
    }

    private fun openHomeScreen() {
        //TO/DO("Not yet implemented")
    }

}