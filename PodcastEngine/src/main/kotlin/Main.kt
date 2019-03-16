package podcastengine

fun main(args: Array<String>) {

    var url: String
    var outputDir : String

    // Ask for the RSS URL
    do {
        print("Podcast RSS: ")
        url = readLine()!!
    } while (url == "")

    // Ask for the download location
    print("Download to (download):")
    outputDir = readLine()!!
    if (outputDir == "") {
        outputDir = "download"
    }

    println("TODO: Download podcasts from $url to $outputDir")
}