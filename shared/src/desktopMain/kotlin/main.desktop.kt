import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.unit.Dp
import org.jetbrains.compose.animatedimage.Blank
import org.jetbrains.compose.animatedimage.animate
import org.jetbrains.compose.animatedimage.loadResourceAnimatedImage
import org.jetbrains.compose.resources.loadOrNull
import java.awt.Desktop
import java.net.URI
import java.util.Locale

actual fun getPlatformName(): String = "Desktop"

actual fun openBrowser(url: String) {
    val uri = URI(url)
    val osName by lazy(LazyThreadSafetyMode.NONE) { System.getProperty("os.name").lowercase(Locale.getDefault()) }
    val desktop = Desktop.getDesktop()
    when {
        Desktop.isDesktopSupported() && desktop.isSupported(Desktop.Action.BROWSE) -> desktop.browse(uri)
        "mac" in osName -> Runtime.getRuntime().exec("open $uri")
        "nix" in osName || "nux" in osName -> Runtime.getRuntime().exec("xdg-open $uri")
        else -> throw RuntimeException("cannot open $uri")
    }
}

@Composable fun MainView() = App()

@Preview
@Composable
fun AppPreview() {
    App()
}

@Composable
actual fun GifWrap(gifResource: String, show: Boolean, size: Dp) {
    if (show) {
        Image(
            loadOrNull { loadResourceAnimatedImage(gifResource) }?.animate() ?: ImageBitmap.Blank,
            contentDescription = null,
            Modifier.size(size)
        )
    }
}