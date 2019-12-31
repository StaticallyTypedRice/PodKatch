package podcastengine.objects

/**
 * An object for conveying the source of a podcast.
 *
 * @param _type The type of source.
 *              This should be used to determine which object to create from the source variable (File, URL, etc).
 *
 * @param _source A string containing the source
 */
class PodcastSource(_type: PodcastSourceType, _source: String) {
    var type: PodcastSourceType = _type
    var source: String = _source
}
