import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import net.maiatoday.giith.demos.a_memphis.MemphisScreen
import net.maiatoday.giith.demos.b_stars.StarsScreen
import net.maiatoday.giith.demos.c_wallpaper.WallpaperScreen
import net.maiatoday.giith.demos.d_blink.BlinkScreen
import net.maiatoday.giith.demos.e_visitorcounter.VisitorCounterScreen
import net.maiatoday.giith.demos.f_rainbowtext.RainbowTextScreen
import net.maiatoday.giith.demos.g_heartpath.HeartPathScreen
import net.maiatoday.giith.demos.h_underconstruction.UnderConstructionScreen
import net.maiatoday.giith.demos.i_glitter.GlitterPointerScreen
import net.maiatoday.giith.navigation.Screen
import net.maiatoday.giith.navigation.roots.GuestBookScreen
import net.maiatoday.giith.navigation.roots.HomeScreen
import net.maiatoday.giith.navigation.roots.MyBookmarksScreen
import net.maiatoday.giith.ui.GrooveTheme
import net.maiatoday.giith.ui.ThemeChoice
import net.maiatoday.giith.ui.colorSwatches.ColorSwatchesScreen
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun App() {
    var groovy by remember { mutableStateOf(false) }
    var screenState by remember { mutableStateOf(Screen.Home) }
    var themeChoice by remember { mutableStateOf(ThemeChoice.Comic) }
    GrooveTheme(themeChoice = themeChoice) {
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Button(onClick = { screenState = Screen.Home }) { Text("Home") }
                Switch(
                    checked = themeChoice == ThemeChoice.Comic,
                    onCheckedChange = {
                        when (themeChoice) {
                            ThemeChoice.Comic -> themeChoice = ThemeChoice.Times
                            ThemeChoice.Times -> themeChoice = ThemeChoice.Comic
                        }
                    }
                )
                Switch(
                    checked = groovy,
                    onCheckedChange = {
                        groovy = !groovy
                    }
                )

            }
            when (screenState) {
                Screen.Home -> HomeScreen(groovy) { screen -> screenState = screen }
                Screen.ColorSwatches -> ColorSwatchesScreen()
                Screen.Memphis -> MemphisScreen()
                Screen.Blink -> BlinkScreen()
                Screen.RainbowText -> RainbowTextScreen()
                Screen.VisitorCounter -> VisitorCounterScreen()
                Screen.GlitterPointer -> GlitterPointerScreen()
                Screen.UnderConstruction -> UnderConstructionScreen()
                Screen.Wallpaper -> WallpaperScreen()
                Screen.HeartPath -> HeartPathScreen()
                Screen.GuestBook -> GuestBookScreen()
                Screen.MyBookmarks -> MyBookmarksScreen()
                Screen.Stars -> StarsScreen()
            }
        }
    }
}

expect fun getPlatformName(): String
fun supportsGifs():Boolean = getPlatformName() != "Android"
fun isAndroid():Boolean = getPlatformName() == "Android"
expect fun openBrowser(url:String)
@Composable
expect fun GifWrap(gifResource: String, show:Boolean = true, size: Dp = 100.dp)
