package com.vereshchagin.nikolay.pepegafood

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.vereshchagin.nikolay.pepegafood.db.BasketDao
import com.vereshchagin.nikolay.pepegafood.db.CatalogDao
import com.vereshchagin.nikolay.pepegafood.model.BasketItem
import com.vereshchagin.nikolay.pepegafood.model.CatalogItem
import com.vereshchagin.nikolay.pepegafood.ui.home.repository.db.HomeDao
import com.vereshchagin.nikolay.pepegafood.ui.home.repository.model.CatalogHomeItem
import com.vereshchagin.nikolay.pepegafood.ui.home.repository.model.FavoriteBasket
import com.vereshchagin.nikolay.pepegafood.ui.home.repository.model.ShoppingBasket

/**
 * Главная БД приложения.
 */
@Database(
    entities = [ShoppingBasket::class, FavoriteBasket::class, CatalogHomeItem::class,
                CatalogItem::class, BasketItem::class],
    version = 1,
    exportSchema = false
)
abstract class MainApplicationDatabase : RoomDatabase() {

    /**
     * Dao для главной страницы приложения.
     */
    abstract fun home() : HomeDao

    /**
     * Dao для каталога.
     */
    abstract fun catalog() : CatalogDao

    /**
     * Dao для корзины.
     */
    abstract fun basket() : BasketDao


    companion object {

        /**
         * Singleton БД.
         */
        @Volatile
        private var INSTANCE: MainApplicationDatabase? = null

        /**
         * Возвращает объект БД.
         * @param context контекст для создания БД.
         */
        fun database(context: Context): MainApplicationDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MainApplicationDatabase::class.java,
                    "main_application_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}