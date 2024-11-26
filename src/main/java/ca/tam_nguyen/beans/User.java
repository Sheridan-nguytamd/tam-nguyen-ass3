package ca.tam_nguyen.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

	private Long id;
	@NonNull
	private String name;
	@NonNull
	private String email;
	private Integer age;
	private String gender;
	private Double weight;
	private Double height;
	
	
}
