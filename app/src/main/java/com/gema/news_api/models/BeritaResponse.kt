package com.gema.news_api.models

data class BeritaResponse(val success: Boolean, val message: String, val data: Array<ListItems>
){
    data class ListItems(
        val id: String,
        val judul: String,
        val isi: String,
        val tgl_berita: String,
        val gambar: String,
        val rating: Double
    )
}
