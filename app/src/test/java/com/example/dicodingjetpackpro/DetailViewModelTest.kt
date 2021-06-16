package com.example.dicodingjetpackpro

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.dicodingjetpackpro.data.FakeRepository
import com.example.dicodingjetpackpro.data.MyRepository
import com.example.dicodingjetpackpro.data.local.LocalDataSource
import com.example.dicodingjetpackpro.data.remote.RemoteDataSource
import com.example.dicodingjetpackpro.di.viewModelModule
import com.example.dicodingjetpackpro.ui.detail.DetailViewModel
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.verify
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.koin.core.component.inject
import org.koin.core.logger.Level
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.mock.MockProviderRule
import org.koin.test.mock.declareMock
import org.mockito.Mockito

class DetailViewModelTest:KoinTest {
    @ExperimentalCoroutinesApi
    @get:Rule
    val koinTestRule = KoinTestRule.create {
        printLogger(Level.NONE)
        modules(
            viewModelModule
        )
    }

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mockProvider = MockProviderRule.create { clazz ->
        Mockito.mock(clazz.java)
    }

    private val detailViewModel by inject<DetailViewModel>()
    private val remoteDataSource = Mockito.mock(RemoteDataSource::class.java)
    private val localDataSource = Mockito.mock(LocalDataSource::class.java)
    private val fakeRepo = FakeRepository(remoteDataSource, localDataSource)

    @Test
    fun getDetailMovie(){
        val movieId = 804435
        val repo = declareMock<MyRepository> {
            runBlocking {
                given(getDetailMovie(movieId)).willReturn(fakeRepo.getDetailMovie())
            }
        }
        val mMovies = detailViewModel.getDetailMovie(movieId).value
        verify(repo).getDetailMovie(movieId)
        TestCase.assertNotNull(mMovies)
    }

    @Test
    fun getDetailShow() {
        val showId = 88396
        val repo = declareMock<MyRepository> {
            runBlocking {
                given(getDetailShow(showId)).willReturn(fakeRepo.getDetailShow())
            }
        }
        val mMovies = detailViewModel.getDetailShow(showId).value
        verify(repo).getDetailShow(showId)
        TestCase.assertNotNull(mMovies)
    }
}