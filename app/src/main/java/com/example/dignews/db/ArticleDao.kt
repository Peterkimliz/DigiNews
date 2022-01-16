package com.example.dignews.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.dignews.models.Articles

@Dao
interface ArticleDao {
    /////////////////////////////////////////////function to insert or update data in room///////////
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upInsert(articles: Articles): Long

    ///////////////////////////////////////////////function to read all data from room///////////////
    @Query("SELECT * FROM articles")
    fun getAllData(): LiveData<List<Articles>>

    //////////////////////////////////////////function to delete an article from room////////////////

    @Delete
    suspend fun deleteArticle(articles: Articles)

}