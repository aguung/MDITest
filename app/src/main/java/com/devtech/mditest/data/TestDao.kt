package com.devtech.mditest.data


import androidx.lifecycle.LiveData
import androidx.room.*
import com.devtech.mditest.data.entity.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TestDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(category: Category)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPoduct(product: Product)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProductCategoryCrossRef(productCategoryCrossRef: ProductCategoryCrossRef)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favorite: Favorite)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCart(cart: Cart)

    @Query("SELECT * FROM category_table")
    fun getCategory(): Flow<List<Category>>

    @Query("SELECT COUNT(cartId) FROM cart_table")
    fun getCartCount(): Flow<Int>

    @Transaction
    @Query("SELECT * FROM product_table WHERE productId IN (SELECT DISTINCT(cartOwnerId) FROM cart_table)")
    fun getProductAndCart(): Flow<List<ProductAndCart>>

    @Transaction
    @Query("SELECT * FROM product_table")
    fun getProductWithCategory(): Flow<List<ProductWithCategory>>

    @Transaction
    @Query("SELECT * FROM category_table WHERE categoryId = :category")
    fun getCategoryWithProduct(category: Int): Flow<List<CategoryWithProduct>>

    @Transaction
    @Query("SELECT * FROM product_table")
    fun getProductAndFavorite(): Flow<List<ProductAndFavorite>>
}