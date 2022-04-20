package ui.smartpro.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class Source(
    val id:@RawValue Any,
    val name: String
): Parcelable