package ca.tam_nguyen.database;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import ca.tam_nguyen.beans.Activity;
import ca.tam_nguyen.beans.User;

@Repository
public class DatabaseAccess {
	
	@Autowired
	private NamedParameterJdbcTemplate jdbc;
	
	// User.java
	
	public List<User> findAllUsers() {
		MapSqlParameterSource namedParameter = new MapSqlParameterSource();
		String query = "SELECT * FROM users";
		return jdbc.query(query, namedParameter,
				new BeanPropertyRowMapper<>(User.class));
	}
	
	public User findUserById(Long id) {
		MapSqlParameterSource namedParameter = new MapSqlParameterSource();
		String query = "SELECT * FROM users WHERE id = :id";
		return jdbc.queryForObject(query, namedParameter,
				new BeanPropertyRowMapper<>(User.class));
	}
	
	public Long saveUser(User user) {
		MapSqlParameterSource namedParameter = new MapSqlParameterSource();
		KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
		String query = "INSERT INTO users (name, email, age, gender, weight, height) " +
						"VALUES (:name, :email, :age, :gender, :weight, :height)";
		namedParameter.addValue("name", user.getName());
		namedParameter.addValue("email", user.getEmail());
		namedParameter.addValue("age", user.getAge());
		namedParameter.addValue("gender", user.getGender());
		namedParameter.addValue("weight", user.getWeight());
		namedParameter.addValue("height", user.getHeight());
		jdbc.update(query, namedParameter, generatedKeyHolder);
		return (Long) generatedKeyHolder.getKey();
	}
	
	public void updateUser(User user) {
		MapSqlParameterSource namedParameter = new MapSqlParameterSource();
		String query = "UPDATE users SET name=:name, email=:email, age=:age, " +
					"gender=:gender, weight=:weight, height=: height WHERE id=:id";
		namedParameter.addValue("id", user.getId());
		namedParameter.addValue("name", user.getName());
		namedParameter.addValue("email", user.getEmail());
		namedParameter.addValue("age", user.getAge());
		namedParameter.addValue("gender", user.getGender());
		namedParameter.addValue("weight", user.getWeight());
		namedParameter.addValue("height", user.getHeight());
		jdbc.update(query, namedParameter);
	}
	
	public void deleteUser(Long id) {
		MapSqlParameterSource namedParameter = new MapSqlParameterSource();
		String query = "DELETE FROM users WHERE id = :id";
		namedParameter.addValue("id", id);
		jdbc.update(query, namedParameter);
	}
	
	// Activity.java
	
	public List<Activity> findActivityByUserId(Long userId) {
		MapSqlParameterSource namedParameter = new MapSqlParameterSource();
		String query = "SELECT * FROM activity WHERE userId = :userId";
		return jdbc.query(query, namedParameter, new BeanPropertyRowMapper<>(Activity.class));
	}
	
	public Activity findActivityById(Long id) {
		MapSqlParameterSource namedParameter = new MapSqlParameterSource();
		String query = "SELECT * FROM activity WHERE id = :id";
		return jdbc.queryForObject(query, namedParameter, new BeanPropertyRowMapper<>(Activity.class));
	}
	
	public Long saveActivity(Activity activity) {
		MapSqlParameterSource namedParameter = new MapSqlParameterSource();
		String query = "INSERT INTO activity(userId, activityType, duration, caloriesBurned, activityDate)" + 
				"VALUES (:userId, :activityType, :duration, :caloriesBurned, :activityDate)";
		KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
		namedParameter.addValue("userId", activity.getUserId());
		namedParameter.addValue("activityType", activity.getActivityType());
		namedParameter.addValue("duration", activity.getDuration());
		namedParameter.addValue("caloriesBurned", activity.getCaloriesBurned());
		namedParameter.addValue("activityDate", activity.getActivityDate());
		jdbc.update(query, namedParameter, generatedKeyHolder);
		return (Long) generatedKeyHolder.getKey();
	}
	
	public void deleteActivity(Long id) {
		MapSqlParameterSource namedParameter = new MapSqlParameterSource();
		String query = "DELETE FROM activity WHERE id = :id";
		namedParameter.addValue("id", id);
		jdbc.update(query, namedParameter);
	}
	
	public void deleteAllActivity() {
		MapSqlParameterSource namedParameter = new MapSqlParameterSource();
		String query = "DELETE FROM activity";
		jdbc.update(query, namedParameter);
	}
}
