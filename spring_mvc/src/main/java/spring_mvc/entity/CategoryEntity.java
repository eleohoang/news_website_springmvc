package spring_mvc.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OrderBy;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "category")
public class CategoryEntity extends BaseEntity {
	@Column(name="name")
	private String name;
	
	@Column(name = "code")
	private String code;

	@OneToMany(mappedBy = "category")
	private List<NewEntity> news = new ArrayList<>();
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<NewEntity> getNews() {
		return news;
	}

	public void setNews(List<NewEntity> news) {
		this.news = news;
	}
}
