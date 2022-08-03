package ru.job4j.io;

import static org.hamcrest.Matchers.nullValue;
import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;


public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Maksim"));
        assertThat(config.value("surname"), is("pass"));
    }
    @Test
    public void whenPairWithComment() {
        Config config = new Config("./data/pair_with_comment.properties");
        config.load();
        assertThat(config.value("#Comment"), is(nullValue()));
    }

    @Test
    public void whenPairWithCommentAndEmpty() {
        String path = "./data/pair_with_comment_and_empty.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value(""), is(nullValue()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenPairWithInvalidPattern() {
        String path = "./data/pair_with_invalid_pattern.properties";
        Config config = new Config(path);
        config.load();
    }
}