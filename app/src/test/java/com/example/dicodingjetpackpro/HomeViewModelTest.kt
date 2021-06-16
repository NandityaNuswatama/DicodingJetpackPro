package com.example.dicodingjetpackpro

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.dicodingjetpackpro.data.FakeRepository
import com.example.dicodingjetpackpro.data.MyRepository
import com.example.dicodingjetpackpro.data.local.LocalDataSource
import com.example.dicodingjetpackpro.data.remote.RemoteDataSource
import com.example.dicodingjetpackpro.di.databaseModule
import com.example.dicodingjetpackpro.di.networkModule
import com.example.dicodingjetpackpro.di.repositoryModule
import com.example.dicodingjetpackpro.di.viewModelModule
import com.example.dicodingjetpackpro.ui.home.HomeViewModel
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.verify
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.koin.core.logger.Level
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject
import org.koin.test.mock.MockProviderRule
import org.koin.test.mock.declareMock
import org.mockito.Mockito

class HomeViewModelTest: KoinTest {
    @ExperimentalCoroutinesApi
    @get:Rule
    val koinTestRule = KoinTestRule.create {
        printLogger(Level.NONE)
        modules(
            viewModelModule, repositoryModule, networkModule, databaseModule
        )
    }

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mockProvider = MockProviderRule.create { clazz ->
        Mockito.mock(clazz.java)
    }

    private val homeViewModel by inject<HomeViewModel>()
    private val remoteDataSource = Mockito.mock(RemoteDataSource::class.java)
    private val localDataSource = Mockito.mock(LocalDataSource::class.java)
    private val fakeRepo = FakeRepository(remoteDataSource, localDataSource)

    @Test
    fun getMovieListTest(){
        val repo = declareMock<MyRepository> {
            runBlocking {
                given(getMovieList()).willReturn(fakeRepo.getMovieList())
            }
        }
        val mMovies = homeViewModel.getMovieList.value
        verify(repo).getMovieList()
        TestCase.assertNotNull(mMovies)
    }

    @Test
    fun getShowListTest(){
        val repo = declareMock<MyRepository> {
            runBlocking {
                given(getShowList()).willReturn(fakeRepo.getShowList())
            }
        }
        val mShows = homeViewModel.getShowList.value
        verify(repo).getMovieList()
        TestCase.assertNotNull(mShows)
    }
}