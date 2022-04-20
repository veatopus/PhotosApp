package kg.ruslan.feature_greeting.ui.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kg.ruslan.core.models.Company
import kg.ruslan.core.ui.DefDiffUtil
import kg.ruslan.feature_greeting.databinding.ItemCompanyBinding

class CompaniesAdapter: ListAdapter<Company, CompaniesAdapter.CompanyViewHolder>(DefDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompanyViewHolder {
        return CompanyViewHolder(ItemCompanyBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: CompanyViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    inner class CompanyViewHolder(private val binding: ItemCompanyBinding): RecyclerView.ViewHolder(binding.root) {
        fun onBind(company: Company) {
            binding.tvCompany.text = company.name
            binding.tvPosition.text = company.position
        }
    }

}