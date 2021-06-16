package com.example.dicodingjetpackpro.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.dicodingjetpackpro.data.local.LocalDataSource
import com.example.dicodingjetpackpro.data.remote.RemoteDataSource
import com.example.dicodingjetpackpro.di.repositoryModule
import com.example.dicodingjetpackpro.utils.Dummy
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test
import org.koin.core.logger.Level
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.mockito.Mockito

class MyRepositoryTest : KoinTest {
    @ExperimentalCoroutinesApi
    @get:Rule
    val koinTestRule = KoinTestRule.create {
        printLogger(Level.NONE)
        modules(
            repositoryModule
        )
    }

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remoteDataSource = Mockito.mock(RemoteDataSource::class.java)
    private val localDataSource = Mockito.mock(LocalDataSource::class.java)
    private val fakeRepo = FakeRepository(remoteDataSource, localDataSource)

    @Test
    fun getMovieListTest() {
        doAnswer {
            (it.arguments[0] as RemoteDataSource.ModelCallback)
                .onReceived(Dummy.dummyMovies())
            it
        }.`when`(remoteDataSource).getMovieList(any())
        val mMovies = fakeRepo.getMovieListRepo().value
        verify(remoteDataSource).getMovieList(any())
        TestCase.assertNotNull(mMovies)
    }

    @Test
    fun getShowListTest() {
        doAnswer {
            (it.arguments[0] as RemoteDataSource.ModelCallback)
                .onReceived(Dummy.dummyShows())
            it
        }.`when`(remoteDataSource).getShowList(any())
        val mShows = fakeRepo.getShowListRepo().value
        verify(remoteDataSource).getShowList(any())
        TestCase.assertNotNull(mShows)
    }

    @Test
    fun getDetailMovieTest(){
        val movieId = 804435
        doAnswer {
            (it.arguments[0] as RemoteDataSource.DetailCallback)
                .onReceived(Dummy.dummyMovies()[0])
            it
        }.`when`(remoteDataSource).getDetailMovie(any(), eq(movieId))
        val mMovie = fakeRepo.getDetailMovieRepo().value
        verify(remoteDataSource).getDetailMovie(any(), eq(movieId))
        TestCase.assertNotNull(mMovie)
    }

    @Test
    fun getDetailShowTest(){
        val showId = 88396
        doAnswer {
            (it.arguments[0] as RemoteDataSource.DetailCallback)
                .onReceived(Dummy.dummyMovies()[0])
            it
        }.`when`(remoteDataSource).getDetailShow(any(), eq(showId))
        val mShows = fakeRepo.getDetailShowRepo().value
        verify(remoteDataSource).getDetailShow(any(), eq(showId))
        TestCase.assertNotNull(mShows)
    }
}