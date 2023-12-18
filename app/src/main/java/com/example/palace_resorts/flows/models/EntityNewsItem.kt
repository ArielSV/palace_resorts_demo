package com.example.palace_resorts.flows.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.palace_resorts.base.db.BaseNewsEntity
import com.example.palace_resorts.flows.constants.DBConstants.ID
import com.example.palace_resorts.flows.constants.DBConstants.NEWS_TABLE
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = NEWS_TABLE)
data class EntityNewsItem(
    @PrimaryKey @ColumnInfo(name = ID) override val id: String,
) : BaseNewsEntity(), Parcelable