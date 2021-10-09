package com.aplikasi.crud_room.model

import com.google.gson.annotations.SerializedName

data class Response(

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: Int? = null
)
