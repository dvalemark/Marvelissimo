package com.projects.disav.marvelissimo


import android.support.test.espresso.Espresso
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.projects.disav.marvelissimo.ui.searchresults.characters.RecyclerAdapterCharacter
import com.projects.disav.marvelissimo.ui.searchresults.comics.RecyclerAdapterComic
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class EspressoMainTests{

    @Rule
    @JvmField
    val rule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun clickComicsPartOfScreen(){
        Espresso.onView(withId(R.id.comic_bg)).perform(click())
        Espresso.onView(withId(R.id.my_recycler_view)).check(matches(isDisplayed()))

    }

    @Test
    fun clickCharacterPartOfScreen(){
        Espresso.onView(withId(R.id.character_bg)).perform(click())
        Espresso.onView(withId(R.id.my_recycler_view)).check(matches(isDisplayed()))
    }

    @Test
    fun characterSearchBarWithSearchResult(){
        Espresso.onView(withId(R.id.character_bg)).perform(click())
        Espresso.onView(ViewMatchers.withId(R.id.search)).perform(click())
        Espresso.onView(ViewMatchers.withId(R.id.search_src_text)).perform(ViewActions.typeText("dead"))
        Espresso.onView(ViewMatchers.withId(R.id.search_src_text)).perform(pressImeActionButton())
        Espresso.onView(ViewMatchers.withId(R.id.my_recycler_view)).check(matches(isDisplayed()))
    }

    @Test
    fun characterSearchBarWithNoSearchResult(){
        Espresso.onView(withId(R.id.character_bg)).perform(click())
        Espresso.onView(ViewMatchers.withId(R.id.search)).perform(click())
        Espresso.onView(ViewMatchers.withId(R.id.search_src_text)).perform(ViewActions.typeText("jkrjgrjrg"))
        Espresso.onView(ViewMatchers.withId(R.id.search_src_text)).perform(pressImeActionButton())
        Thread.sleep(10000)
        Espresso.onView(ViewMatchers.withId(R.id.my_recycler_view)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)))
    }

    @Test
    fun comicSearchBarWithNoSearchResult(){
        Espresso.onView(withId(R.id.comic_bg)).perform(click())
        Espresso.onView(ViewMatchers.withId(R.id.search)).perform(click())
        Espresso.onView(ViewMatchers.withId(R.id.search_src_text)).perform(ViewActions.typeText("jkrjgrjrg"))
        Espresso.onView(ViewMatchers.withId(R.id.search_src_text)).perform(pressImeActionButton())
        Thread.sleep(10000)
        Espresso.onView(ViewMatchers.withId(R.id.my_recycler_view)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)))
    }


    @Test
    fun comicSearchBarWithSearchResult(){
        Espresso.onView(withId(R.id.comic_bg)).perform(click())
        Espresso.onView(ViewMatchers.withId(R.id.search)).perform(click())
        Espresso.onView(ViewMatchers.withId(R.id.search_src_text)).perform(ViewActions.typeText("dead"))
        Espresso.onView(ViewMatchers.withId(R.id.search_src_text)).perform(pressImeActionButton())
        Espresso.onView(ViewMatchers.withId(R.id.my_recycler_view)).check(matches(isDisplayed()))
    }



    @Test
    fun characterClickOnResultinRecyclerview(){
        Espresso.onView(withId(R.id.character_bg)).perform(click())
        Thread.sleep(3000)
        Espresso.onView(withId(R.id.my_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerAdapterCharacter.ItemHolder>(0, click()))
        Espresso.onView(withId(R.id.character_image)).check(matches(isDisplayed()))
    }

    @Test
    fun comicClickOnResultinRecyclerview(){
        Espresso.onView(withId(R.id.comic_bg)).perform(click())
        Thread.sleep(3000)
        Espresso.onView(withId(R.id.my_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerAdapterComic.ItemHolder>(0, click()))
        Espresso.onView(withId(R.id.comic_image)).check(matches(isDisplayed()))
    }

    @Test
    fun scrollingComicsRecyclerList(){
        Espresso.onView(withId(R.id.comic_bg)).perform(click())
        Thread.sleep(3000)
        Espresso.onView(withId(R.id.my_recycler_view))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerAdapterComic.ItemHolder>(20))
        Thread.sleep(3000)
        Espresso.onView(withId(R.id.my_recycler_view))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerAdapterComic.ItemHolder>(40))
    }

    @Test
    fun scrollingCharactersRecyclerList(){
        Espresso.onView(withId(R.id.character_bg)).perform(click())
        Thread.sleep(3000)
        Espresso.onView(withId(R.id.my_recycler_view))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerAdapterCharacter.ItemHolder>(20))
        Thread.sleep(3000)
        Espresso.onView(withId(R.id.my_recycler_view))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerAdapterCharacter.ItemHolder>(40))
    }

   /* @Test
    fun clickMenuInCharacterToGoToComic(){
        Espresso.onView(ViewMatchers.withId(R.id.character_bg))
        Thread.sleep(2000)
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getInstrumentation().getTargetContext())
        println("opened")
        Thread.sleep(3000)
        Espresso.onView(ViewMatchers.withId(R.id.comic_menu)).perform(click())
        Thread.sleep(3000)
        Espresso.onView(ViewMatchers.withId(R.id.comicImage)).check(matches(isDisplayed()))
    }*/


}

