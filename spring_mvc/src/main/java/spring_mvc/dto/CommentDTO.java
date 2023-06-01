package spring_mvc.dto;

public class CommentDTO extends AbstractDTO<CommentDTO>{
	private String content;
	private Long newId;
	private Long userId;
	private String userName;
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Long getNewId() {
		return newId;
	}
	public void setNewId(Long newId) {
		this.newId = newId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
}
