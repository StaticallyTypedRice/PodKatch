createTable:
CREATE TABLE podcasts (
    title TEXT NOT NULL,        -- The podcast title
    source TEXT NOT NULL,       -- The podcast source
    link TEXT,                  -- The link to the podcast page
    description TEXT,           -- The podcast description
    image TEXT,                 -- The cover image
    author TEXT,                -- The podcast author
    category TEXT,              -- The podcast category

    -- Whether the podcast is set as explicit for iTunes
    -- Set by the author, not iTunes
    -- Doesn't prove that the podcast isn't explicit if false or null
    -- Not guaranteed to be an accurate indicator, not sure if moderated by iTunes (probably not)
    itunes_explicit BOOLEAN,

    lang TEXT,                  -- The podcast language
    copyright TEXT,             -- The copyright notice
);

