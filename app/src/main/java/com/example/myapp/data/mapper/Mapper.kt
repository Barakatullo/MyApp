package com.example.myapp.data.mapper

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.myapp.data.network.model.CurrencyDto
import com.example.myapp.domain.entity.Currency
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class Mapper @Inject constructor() {

    fun mapDtoToModel(dto: CurrencyDto) = Currency(
        charCode = dto.charCode,
        price = dto.price,
        name = dto.name,
    )
     @RequiresApi(Build.VERSION_CODES.O)
     fun convertTimestampToTime(timestamp: String): String {
         val date = OffsetDateTime.parse(timestamp)
         val  formatter = DateTimeFormatter.ofPattern("HH:mm.dd.MM.yyyy")
        return date.format(formatter)
    }
}