createTable:
CREATE TABLE podcasts (
    title TEXT NOT NULL,        -- The episode title
    source TEXT NOT NULL,       -- The episode source
    guid TEXT,                  -- The episode GUID
    description TEXT,           -- The podcast description
    pubdate DATE NOT NULL,      -- The episode publication date
    duration TIME NOT NULL,     -- The episode duration
    type TEXT,                  -- The episode type (based on iTunes' episode types)


    -- Whether the podcast is set as explicit for iTunes
    -- Set by the author, not iTunes
    -- Doesn't prove that the podcast isn't explicit if false or null
    -- Not guaranteed to be an accurate indicator, not sure if moderated by iTunes (probably not)
    itunes_explicit BOOLEAN,

    downloaded BOOLEAN NOT NULL,-- Whether the episode has been downloaded
    path TEXT,                  -- The path to the local episode file
);
