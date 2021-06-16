package com.example.dicodingjetpackpro

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.DataSource
import com.example.dicodingjetpackpro.data.FakeRepository
import com.example.dicodingjetpackpro.data.MyRepository
import com.example.dicodingjetpackpro.data.local.LocalDataSource
import com.example.dicodingjetpackpro.data.local.entity.MyModelEntity
import com.example.dicodingjetpackpro.data.remote.RemoteDataSource
import com.example.dicodingjetpackpro.di.databaseModule
import com.example.dicodingjetpackpro.di.networkModule
import com.example.dicodingjetpackpro.di.repositoryModule
import com.example.dicodingjetpackpro.di.viewModelModule
import com.example.dicodingjetpackpro.ui.favorite.FavoriteViewModel
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
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`

class FavoriteViewModelTest: KoinTest {
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

    private val favoriteViewModel by inject<FavoriteViewModel>()
    private val remoteDataSource = Mockito.mock(RemoteDataSource::class.java)
    private val localDataSource = Mockito.mock(LocalDataSource::class.java)
    private val fakeRepo = FakeRepository(remoteDataSource, localDataSource)

    @Test
    fun getFavMovies(){
        val repo = declareMock<MyRepository> {
            runBlocking {
                given(getMoviesListTest()).willReturn(fakeRepo.getMoviesList())
            }
        }
        val mMovies = favoriteViewModel.getFavMovieTest()
        verify(repo).getMoviesListTest()
        TestCase.assertNotNull(mMovies)
    }

    @Test
    fun getFavShows(){
        val repo = declareMock<MyRepository> {
            runBlocking {
                given(getShowListTest()).willReturn(fakeRepo.getShowsList())
            }
        }
        val mShows = favoriteViewModel.getFavShowTest()
        verify(repo).getShowListTest()
        TestCase.assertNotNull(mShows)
    }
}