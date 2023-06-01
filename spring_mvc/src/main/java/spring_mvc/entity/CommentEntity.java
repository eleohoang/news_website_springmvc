package spring_mvc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "comment")
public class CommentEntity extends BaseEntity {
	@Column(name="content")
	private String content;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "new_id")
    private NewEntity newEntity;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public NewEntity getNewEntity() {
		return newEntity;
	}

	public void setNewEntity(NewEntity newEntity) {
		this.newEntity = newEntity;
	}

	public UserEntity getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}
}
