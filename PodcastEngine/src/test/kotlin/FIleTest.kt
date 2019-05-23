import org.junit.Test
import org.junit.Assert.*

import java.net.URL

import podcastengine.file.*

class ParsingTest {
    @Test
    fun `Test URL to FileName parsing`() {
        assertEquals(FileName(""), parseFilenameFromUrl(URL("https://example.com")))
        assertEquals(FileName(""), parseFilenameFromUrl(URL("https://example.com/")))
        assertEquals(FileName("file"), parseFilenameFromUrl(URL("https://example.com/file")))
        assertEquals(FileName("file"), parseFilenameFromUrl(URL("https://example.com/file/")))
        assertEquals(FileName("file.txt"), parseFilenameFromUrl(URL("https://example.com/file.txt")))
        assertEquals(FileName("file.txt"), parseFilenameFromUrl(URL("https://example.com/folder/file.txt")))
        assertEquals(FileName("file.txt"), parseFilenameFromUrl(URL("https://example.com/folder/file.txt?a=1&b=2")))
        assertEquals(FileName("file.txt"), parseFilenameFromUrl(URL("https://example.com/folder/file.txt?a=1&b=2#c")))
    }
}

