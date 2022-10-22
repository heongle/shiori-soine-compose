import java.net.URI
import kotlin.io.path.toPath

object AppGlobal {
    val rootPath: String = resolveRootPath()
    private fun resolveRootPath(): String {
        val codePath = AppGlobal::class.java.protectionDomain.codeSource.location.toString()
        val newPath = codePath.substringBeforeLast("/").substringBeforeLast("/")
        return URI(newPath).toPath().toAbsolutePath().toString()
    }
}
