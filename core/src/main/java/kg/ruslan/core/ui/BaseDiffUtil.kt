package kg.ruslan.core.ui

import androidx.recyclerview.widget.DiffUtil.ItemCallback

private fun<T> defComparator(oldItem: T, newItem: T) = newItem == oldItem

class DefDiffUtil<T>(
    private inline val similarityOfItems: (oldItem: T, newItem: T) -> Boolean = ::defComparator,
    private inline val similarityOfItemsContent: (oldItem: T, newItem: T) -> Boolean = ::defComparator
): ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T) = similarityOfItems(oldItem, newItem)

    override fun areContentsTheSame(oldItem: T, newItem: T) = similarityOfItemsContent(oldItem, newItem)
}