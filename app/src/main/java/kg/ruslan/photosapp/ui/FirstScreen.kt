package kg.ruslan.photosapp.ui

import kg.ruslan.core.base.BaseFragment
import kg.ruslan.photosapp.databinding.FragmentSplashScreenBinding


class FirstScreen : BaseFragment<FragmentSplashScreenBinding>(FragmentSplashScreenBinding::inflate) {

    override fun initialize() {
        val didGreetingScreenOpen: Boolean = TODO("use shared people to know in")
        if (didGreetingScreenOpen) {
            openHomeScreen()
        } else {
            openGreetingScreen()
        }
    }

    private fun openGreetingScreen() {
        TODO("Not yet implemented")
    }

    private fun openHomeScreen() {
        TODO("Not yet implemented")
    }

}