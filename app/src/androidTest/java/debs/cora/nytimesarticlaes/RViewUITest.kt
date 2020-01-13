package debs.cora.nytimesarticlaes


import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test
import java.util.regex.Matcher

class RViewUITest {
    @Rule
    @JvmField
    var activityRule = ActivityTestRule<ItemListActivity>(ItemListActivity::class.java)

    @Test
    fun countPrograms() {
        onView(withId(R.id.NYTimesArticlesRecycleView))
            .perform(waitFor(10000))
            .check(matches(CustomMatchers.withItemCount(20)))
    }

    @Test
    fun countProgramsWithViewAssertion() {
        onView(withId(R.id.NYTimesArticlesRecycleView))
            .perform(waitFor(10000))
            .check(CustomAssertions.hasItemCount(20))
    }

    //************************* Not the perfect solution, but we wait for few seconds in order to let the RView load its data
    fun waitFor(delay: Long): ViewAction {
        return object : ViewAction {
            override fun perform(uiController: UiController?, view: View?) {
                uiController?.loopMainThreadForAtLeast(delay)
            }

            override fun getConstraints(): org.hamcrest.Matcher<View> {
                return isRoot()
            }

            override fun getDescription(): String {
                return "wait for " + delay + "milliseconds"
            }
        }
    }
    }