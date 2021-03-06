import org.junit.Test
import org.junit.Assert.*

import java.net.URL

import podcastengine.file.*

class ParsingTest {
    @Test
    fun `Test file extension parsing`() {
        assertEquals("", FileName("").extension)
        assertEquals("", FileName("file").extension)
        assertEquals("", FileName("file.").extension)
        assertEquals("txt", FileName(".txt").extension)
        assertEquals("txt", FileName("file.txt").extension)
        assertEquals("txt", FileName("file.name.txt").extension)
    }

    @Test
    fun `Test URL to FileName parsing`() {
        assertEquals(FileName("").name, parseFilenameFromUrl(URL("https://example.com")).name)
        assertEquals(FileName("").name, parseFilenameFromUrl(URL("https://example.com/")).name)
        assertEquals(FileName("file").name, parseFilenameFromUrl(URL("https://example.com/file")).name)
        assertEquals(FileName("file").name, parseFilenameFromUrl(URL("https://example.com/file/")).name)
        assertEquals(FileName("file.txt").name, parseFilenameFromUrl(URL("https://example.com/file.txt")).name)
        assertEquals(FileName("file.txt").name, parseFilenameFromUrl(URL("https://example.com/folder/file.txt")).name)
        assertEquals(FileName("file.txt").name, parseFilenameFromUrl(URL("https://example.com/folder/file.txt?a=1&b=2")).name)
        assertEquals(FileName("file.txt").name, parseFilenameFromUrl(URL("https://example.com/folder/file.txt?a=1&b=2#c")).name)
    }
}

