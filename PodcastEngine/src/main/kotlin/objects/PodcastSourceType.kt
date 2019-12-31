package podcastengine.objects

/**
 * The supported podcast source types.
 * More types will be added as support for them are implemented.
 */
enum class PodcastSourceType {
    URL {
        override fun toString(): String {
            return "url"
        }
    },
    FILE {
        override fun toString(): String {
            return "url"
        }
    };
}
