package ca.tam_nguyen.beans;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Activity {
	
	private Long id;
	@NonNull
	private Long userId;
	@NonNull
	private String activityType;
	private Integer duration;
	private Double caloriesBurned;
	private LocalDateTime activityDate = LocalDateTime.now();
	
}
