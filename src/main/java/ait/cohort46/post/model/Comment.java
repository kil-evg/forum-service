package ait.cohort46.post.model;

import java.time.LocalDateTime;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = {"user", "dateCreated"})
public class Comment {
	@Setter
	private String user;
	@Setter
	private String message;
	private LocalDateTime dateCreated = LocalDateTime.now();
	private int likes;

	public Comment(String user, String message) {
		this.user = user;
		this.message = message;
	}
	
	public void addLike() {
		likes++;
	}

}
