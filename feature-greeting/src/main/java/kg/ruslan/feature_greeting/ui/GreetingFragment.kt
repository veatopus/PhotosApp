package kg.ruslan.feature_greeting.ui

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kg.ruslan.core.base.BaseFragment
import kg.ruslan.core.models.Candidate
import kg.ruslan.core.models.toNormalString
import kg.ruslan.core.sharedpreference.GreetingFragmentOpened
import kg.ruslan.core.ui.dismissProgress
import kg.ruslan.core.ui.load
import kg.ruslan.core.ui.showProgress
import kg.ruslan.feature_greeting.databinding.FragmentGreetingBinding
import kg.ruslan.feature_greeting.ui.recyclerview.CompaniesAdapter
import kotlinx.coroutines.flow.StateFlow
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class GreetingFragment : BaseFragment<FragmentGreetingBinding>(FragmentGreetingBinding::inflate) {

    private val adapter: CompaniesAdapter by lazy {
        CompaniesAdapter()
    }
    private val viewModel: GreetingViewModel by viewModel()
    private val greetingFragmentOpened: GreetingFragmentOpened by inject()

    override fun initialize() {
        greetingFragmentOpened.greetingFragmentOpened()
        viewModel.handleSideEffects(GreetingUIIntents.GetCandidate)
        initToolbar()
        bindState(state = viewModel.state)
    }

    private fun bindState(state: StateFlow<GreetingState>) {
        state.partialListener(::initData) { it.data }
        state.partialListener(::progress) { it.isLoading }
    }

    private fun initToolbar() {
        (requireActivity() as AppCompatActivity?)?.run {
            setSupportActionBar(binding.toolbar)
            title = this.title
        }
    }

    private fun progress(isProgress: Boolean) {
        if (isProgress) showProgress(title = "loading candidate")
        else dismissProgress()
    }

    private fun initData(data: Candidate?) {
        if (data != null) {
            binding.run {
                photoOfUser.load(data.photoLink)
                content.run {
                    tvEducation.text = data.education.toNormalString().plus("!")
                    tvUserName.text = data.name
                    tvUserSecondName.text = data.secondName.plus("!")
                    tvUserDescription.text = data.description
                    tvCompanies.adapter = this@GreetingFragment.adapter
                    adapter.submitList(data.companies)
                }
            }
        }
    }

    private fun setListenerWithUIIntent(view: View, intent: GreetingUIIntents) {
        view.setOnClickListener {
            viewModel.handleSideEffects(intent)
        }
    }
}