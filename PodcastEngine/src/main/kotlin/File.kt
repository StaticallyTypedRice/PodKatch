package podcastengine.file

import java.net.URL
import java.util.NoSuchElementException

class FileName(_name: String, _noExtraSpaces: Boolean = true) {

    companion object {
        val invalidFilenameCharacters = arrayOf(
                '\\',
                '/',
                ':',
                '*',
                '?',
                '"',
                '<',
                '>',
                '|'
        )
    }

    val noExtraSpaces = _noExtraSpaces      // Whether to remove extra spaces when parsing the file name
    val name = parse(_name)                 // Automatically parse the filename when initializing the object
    val extension = parseExtension(name)    // The file extension

    /**
     * Removes invalid file name characters.
     *
     * @param name The string to be parsed.
     * @param placeholder Replace invalid file name characters with this string.
     */
    fun removeInvalidFilenameCharacters(name: String, placeholder: String = ""): String {

        var newName = name

        for (character in invalidFilenameCharacters) {
            newName = newName.replace(character.toString(), placeholder)
        }

        return newName

    }

    /**
     * Removes extra spaces from the file name.
     *
     * @param name The string to be parsed.
     */
    fun removeExtraSpaces(name: String): String {

        return name.replace("\\s+".toRegex(), " ")
    }

    /**
     * Parses a string into a valid file name.
     *
     * @param name The string to be parsed.
     */
    fun parse(name: String): String {

        var newName = name

        newName = removeInvalidFilenameCharacters(newName)

        if (noExtraSpaces) {
            newName = removeExtraSpaces(newName)
        }

        return newName

    }

    /**
     * Parse the file extension.
     *
     * @param name The string to be parsed.
     */
    fun parseExtension(name: String): String {
        var nameList: List<String>
        try {
            nameList = name.split(".").dropLastWhile { it.isEmpty() }

            if (nameList.lastIndex == 0) {
                // The file name does not contain dots and therefore has no extension
                return ""
            } else {
                return nameList.last()
            }
        } catch (e: NoSuchElementException) {
            return ""
        }
    }

}

/**
 * Parses the file name from a URL.
 *  - Gets the final item in a slash character seperated URL.
 *  - Removes query strings.
 *
 * Assumes that the URL will be structured as a standard file path.
 *
 * @param url The URL to parse.
 */
fun parseFilenameFromUrl(url: URL): FileName {
    try {
        return FileName(url.path.split("/").dropLastWhile { it.isEmpty() }.last())
    } catch (e: NoSuchElementException) {
        return FileName("")
    }
}
