package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Post;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcPostDao implements PostDao{
    private final JdbcTemplate jdbcTemplate;

    public JdbcPostDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public List<Post> getPostByUserId(int userId) {
        List<Post> posts = new ArrayList<>();
        String sql = "SELECT * FROM post WHERE user_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
            while (results.next()) {
                Post post = mapRowToPost(results);
                posts.add(post);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return posts;
    }

    @Override
    public Post createPost(Post post) {
        Post newPost = null;
        String sql = "INSERT INTO post (plant_id, user_id, forum_id, post_description, price, quantity, title, post_img, post_category)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING post_id;";
        try {
            int postId = jdbcTemplate.queryForObject(sql, int.class, post.getPlantId(), post.getUserId(), post.getForumId(), post.getPostDescription(),
                    post.getPrice(), post.getQuantity(), post.getTitle(), post.getPostImg(), post.getPostCategory());
            newPost = getPostById(postId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation");
        }
        return newPost;
    }

    @Override
    public Post getPostById(int id) {
        Post post = null;
        String sql = "SELECT * FROM post WHERE post_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
            if (results.next()) {
                post = mapRowToPost(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return post;
    }

    private Post mapRowToPost(SqlRowSet rs) {
        Post post = new Post();
        post.setPostId(rs.getInt("post_id"));
        post.setPlantId(rs.getInt("plant_id"));
        post.setUserId(rs.getInt("user_id"));
        post.setForumId(rs.getInt("forum_id"));
        post.setPostDescription(rs.getString("post_description"));
        post.setPrice(rs.getBigDecimal("price"));
        post.setQuantity(rs.getInt("quantity"));
        post.setTitle(rs.getString("title"));
        post.setPostImg(rs.getString("post_img"));
        post.setPostCategory(rs.getString("post_category"));
        return post;
    }
}
