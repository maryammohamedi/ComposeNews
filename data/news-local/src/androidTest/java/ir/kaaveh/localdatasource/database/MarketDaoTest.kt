package ir.kaaveh.localdatasource.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import ir.kaaveh.localdatasource.test.favoriteLocalNewsDto
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.io.IOException

@OptIn(ExperimentalCoroutinesApi::class)
class MarketDaoTest {

    private lateinit var newsDao: MarketDao
    private lateinit var db: MarketsDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, MarketsDatabase::class.java).build()
        newsDao = db.marketDao
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun emptyTableAtDbInitialization() = runTest {
        val newsList = newsDao.getMarketList().first()
        assertTrue(newsList.isEmpty())
    }

    @Test
    @Throws(Exception::class)
    fun insertNewsToDb() = runTest {
        newsDao.insertMarketList(favoriteLocalNewsDto)
        val newsList = newsDao.getMarketList().first()
        assertTrue(newsList.contains(favoriteLocalNewsDto))
    }

}