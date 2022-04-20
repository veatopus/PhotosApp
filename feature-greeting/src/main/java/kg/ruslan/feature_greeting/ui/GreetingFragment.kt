package kg.ruslan.feature_greeting.ui

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kg.ruslan.core.base.BaseFragment
import kg.ruslan.core.models.Candidate
import kg.ruslan.core.models.toNormalString
import kg.ruslan.core.ui.load
import kg.ruslan.feature_greeting.databinding.FragmentGreetingBinding
import kg.ruslan.feature_greeting.ui.recyclerview.CompaniesAdapter
import kotlinx.coroutines.flow.StateFlow
import org.koin.androidx.viewmodel.ext.android.viewModel

class GreetingFragment : BaseFragment<FragmentGreetingBinding>(FragmentGreetingBinding::inflate) {

    private val adapter: CompaniesAdapter by lazy {
        CompaniesAdapter()
    }
    private val viewModel: GreetingViewModel by viewModel()

    override fun initialize() {
        initToolbar()
        bindState(state = viewModel.state)
    }

    private fun bindState(state: StateFlow<GreetingState>) {
        state.partialListener(::initData) { it.data }
    }

    private fun initToolbar() {
        (requireActivity() as AppCompatActivity?)?.run {
            setSupportActionBar(binding.toolbar)
            title = this.title
        }
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