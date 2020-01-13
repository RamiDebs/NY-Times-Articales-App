package debs.cora.nytimesarticlaes

import NetworkHelper.ArticlesViewModel
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import model.Result
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentCaptor
import utils.mock
import java.util.*

class MainViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var viewModel: ArticlesViewModel

    private val observer: Observer<List<Result>> = mock()

    @Before
    fun before() {
        viewModel = ArticlesViewModel()
        viewModel.getMutableLiveData().observeForever(observer)
    }

    @Test
    fun fetchDataTest() {
        val expectedUser = Result(
           url = "www.nytimes.com/2020/01/05/world/middleeast/Iran-us-trump.html",
            section="World", type="Article",
            title="Outrage in Iran After Killing of Suleimani: Here’s What You Need to Know, abstract=Iran said it would essentially abandon its obligations under a land mark nuclear deal, and the Iraqi Parliament voted to expel American troops.")

        viewModel.createArticleLiveData()

        val captor = ArgumentCaptor.forClass(Result::class.java)
        captor.run {
            verify(observer, times(1)).onChanged(listOf(capture()))
            Assert.assertEquals(expectedUser, value)
        }
    }
}