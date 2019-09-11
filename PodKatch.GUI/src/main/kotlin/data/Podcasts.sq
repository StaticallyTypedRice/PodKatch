createTable:
CREATE TABLE podcasts (
    title TEXT NOT NULL,        -- The podcast title
    source TEXT NOT NULL,       -- The podcast source
    link TEXT NOT NULL,         -- The link to the podcast page
    description TEXT NOT NULL,  -- The podcast description
    image TEXT NOT NULL,        -- The cover image
    author TEXT NOT NULL,       -- The podcast author
    category TEXT NOT NULL,     -- The podcast category

    -- Whether the podcast is set as explicit for iTunes
    -- Set by the author, not iTunes
    -- Doesn't prove that the podcast isn't explicit if false or null
    -- Not guaranteed to be an accurate indicator, not sure if moderated by iTunes (probably not)
    itunes_explicit BOOLEAN NOT NULL,

    lang TEXT NOT NULL,         -- The podcast language
    copyright TEXT NOT NULL,    -- The copyright notice
);

