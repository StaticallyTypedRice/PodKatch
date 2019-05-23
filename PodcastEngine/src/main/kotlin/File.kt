package podcastengine.file

import java.net.URL

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

    val noExtraSpaces = _noExtraSpaces

    // Automatically parse the filename when initializing the object
    val name = parse(_name)

    /**
     * Removes invalid file name characters.
     *
     * @param name The string to be parsed.
     * @param placeholder: Replace invalid file name characters with this string.
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
    return FileName(url.path.split("/").dropLastWhile { it.isEmpty() }.last())
}
