import org.junit.Test
import org.junit.Assert.*

import java.net.URL

import podcastengine.file.*

class ParsingTest {
    @Test
    fun `Test URL to FileName parsing`() {
        assertEquals("", parseFilenameFromUrl(URL("https://example.com")))
        assertEquals("", parseFilenameFromUrl(URL("https://example.com/")))
        assertEquals("file", parseFilenameFromUrl(URL("https://example.com/file")))
        assertEquals("file", parseFilenameFromUrl(URL("https://example.com/file/")))
        assertEquals("file.txt", parseFilenameFromUrl(URL("https://example.com/file.txt")))
        assertEquals("file.txt", parseFilenameFromUrl(URL("https://example.com/folder/file.txt")))
        assertEquals("file.txt", parseFilenameFromUrl(URL("https://example.com/folder/file.txt?a=1&b=2")))
        assertEquals("file.txt", parseFilenameFromUrl(URL("https://example.com/folder/file.txt?a=1&b=2#c")))
    }
}

