CREATE TABLE if NOT EXISTS channel (
  channel_id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  name VARCHAR(255) NOT NULL
);

CREATE TABLE if NOT EXISTS category (
  category_id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS video (
    video_id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    url VARCHAR(100) NOT NULL,
    title VARCHAR(200) NOT NULL,
    thumbnail VARCHAR(100) NOT NULL,
    channel_id INT,

    CONSTRAINT unique_youtube_url UNIQUE (url),
    CONSTRAINT fk_video_channel
        FOREIGN KEY (channel_id)
        REFERENCES channel (channel_id)
        ON DELETE SET NULL
);

CREATE TABLE video_category (
    video_id INTEGER NOT NULL,
    category_id INTEGER NOT NULL,

    PRIMARY KEY (video_id, category_id),

    CONSTRAINT fk_video
        FOREIGN KEY (video_id)
        REFERENCES video(video_id)
        ON DELETE CASCADE,

    CONSTRAINT fk_category
        FOREIGN KEY (category_id)
        REFERENCES category(category_id)
        ON DELETE CASCADE
);