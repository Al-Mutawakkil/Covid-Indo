package com.tugas.covid_indo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tugas.covid_indo.model.DataItem
import kotlinx.android.synthetic.main.list_provinsi.view.*
import java.text.DecimalFormat
import java.text.NumberFormat
import kotlin.collections.ArrayList

class AdapterProvinsi(private val provinsi: ArrayList<DataItem>, private val clickListener: (DataItem) -> Unit ):
    RecyclerView.Adapter<ProvinsiViewHolder>() {

    var listProvinsi = ArrayList<DataItem>()
    init {
        listProvinsi = provinsi
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProvinsiViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_provinsi, parent, false)
        return ProvinsiViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listProvinsi.size
    }

    override fun onBindViewHolder(holder: ProvinsiViewHolder, position: Int) {
        val data = listProvinsi[position]
        holder.bind(data, clickListener)
    }

}

class ProvinsiViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(provinsi: DataItem, clickListener: (DataItem) -> Unit) {
        val nama_provinsi: TextView = itemView.tvNamaProvinsi
        val totalKasus: TextView = itemView.tvTotalKasus
        val totalKematian: TextView = itemView.tvTotalKematian
        val totalSembuh: TextView = itemView.tvTotalSembuh

        val formatter: NumberFormat = DecimalFormat("#,###")

        nama_provinsi.tvNamaProvinsi.text = provinsi.provinsi
        totalKasus.tvTotalKasus.text = formatter.format(provinsi.kasusPosi?.toDouble())
        totalKematian.tvTotalKematian.text = formatter.format(provinsi.kasusMeni?.toDouble())
        totalSembuh.tvTotalSembuh.text = formatter.format(provinsi.kasusSemb?.toDouble())

    }
}
