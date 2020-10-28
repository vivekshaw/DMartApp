package com.ecommerce.dmart.data.localdb.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ecommerce.dmart.data.localdb.entities.ProductEntity

@Dao
interface CharacterDao {

    @Query("SELECT * FROM productentity")
    fun getAllCharacters() : LiveData<List<ProductEntity>>

    @Query("SELECT * FROM productentity WHERE userId = :id")
    fun getCharacter(id: Int): LiveData<ProductEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(characters: List<ProductEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(product: ProductEntity)


}