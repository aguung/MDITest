package com.devtech.mditest.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.devtech.mditest.R
import com.devtech.mditest.data.entity.*
import com.devtech.mditest.di.ApplicationScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

@Database(
    entities = [Category::class, Product::class, ProductCategoryCrossRef::class, Favorite::class, Cart::class],
    version = 1
)
@TypeConverters(ColorConverter::class)
abstract class TestDatabase : RoomDatabase() {

    abstract fun testDao(): TestDao

    class Callback @Inject constructor(
        private val database: Provider<TestDatabase>,
        @ApplicationScope private val applicationScope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            val dao = database.get().testDao()

            applicationScope.launch {
                dao.insertCategory(Category(name = "Chair", image = R.drawable.chair))
                dao.insertCategory(Category(name = "Table", image = R.drawable.table))
                dao.insertCategory(Category(name = "Bed", image = R.drawable.bed))
                dao.insertCategory(Category(name = "Sofa", image = R.drawable.sofa))
                dao.insertPoduct(
                    Product(
                        name = "Wood Chair",
                        image = R.drawable.chair_product,
                        description = "Test Wood Chair",
                        price = 100000,
                        quantity = 4,
                        color = listOf(R.color.merah, R.color.biru, R.color.orange),
                    )
                )
                dao.insertPoduct(
                    Product(
                        name = "Relaxe Chair",
                        image = R.drawable.chair_product,
                        description = "Test Relaxe Chair",
                        price = 150000,
                        quantity = 8,
                        color = listOf(R.color.merah, R.color.hijau, R.color.biru),
                    )
                )
                dao.insertPoduct(
                    Product(
                        name = "Relaxe Chair Second",
                        image = R.drawable.chair_product,
                        description = "Test Relaxe Chair Second",
                        price = 125000,
                        quantity = 12,
                        color = listOf(R.color.orange, R.color.hijau, R.color.biru),
                    )
                )
                dao.insertProductCategoryCrossRef(
                    productCategoryCrossRef = ProductCategoryCrossRef(
                        categoryId = 1,
                        productId = 1
                    )
                )
                dao.insertProductCategoryCrossRef(
                    productCategoryCrossRef = ProductCategoryCrossRef(
                        categoryId = 1,
                        productId = 2
                    )
                )
                dao.insertProductCategoryCrossRef(
                    productCategoryCrossRef = ProductCategoryCrossRef(
                        categoryId = 1,
                        productId = 3
                    )
                )
                dao.insertCart(Cart(cartOwnerId = 1, quantity = 1))
            }
        }
    }
}