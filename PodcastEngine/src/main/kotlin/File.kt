package podcastengine.file

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
