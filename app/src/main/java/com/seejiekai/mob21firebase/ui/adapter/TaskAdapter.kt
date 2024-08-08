package com.seejiekai.mob21firebase.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.tasks.Tasks
import com.seejiekai.mob21firebase.data.model.Task
import com.seejiekai.mob21firebase.databinding.ItemTaskBinding


class TaskAdapter(
    private var tasks: List<Task>
): RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {
    var listener: Listener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding = ItemTaskBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return TaskViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val item = tasks[position]
        holder.bind(item)
    }

    fun setTasks(tasks: List<Task>) {
        this.tasks = tasks
        notifyDataSetChanged()
    }

    inner class TaskViewHolder(
        private val binding: ItemTaskBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(task: Task) {
            binding.tvTitle.text = task.title
            binding.tvDesc.text = task.desc
            binding.ivDelete.setOnClickListener {
                listener?.onDeleteItem(task)
            }

            binding.cvTaskItem.setOnClickListener {
                listener?.onClickItem(task)
            }
        }
    }

    interface Listener {
        fun onClickItem(task: Task)
        fun onDeleteItem(task: Task)
        fun onLongClick(task: Task)
    }
}