package podcastengine.objects

/**
 * An object for conveying the source of a podcast.
 *
 * @param _type A string containing the type of source.
 *              This should be used to determine which object to create from the source variable (File, URL, etc).
 *              The strings are not case sensitive and will always be reduced to all lowercase.
 *
 *              Types (more will be added as support for them are implemented):
 *               - url
 *               - file
 *
 * @param _source A string containing the source
 */
class PodcastSource(_type: String, _source: String) {
    var type: String = _type.toLowerCase()
    var source: String = _source
}
