package com.hygogg.hero.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

@Entity
@Table(name="heroes")
public class Hero {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String alias;
	
	private String realName;
	
	private String description;
	
	@Column(updatable=false)
	private Date createdAt;
	
	private Date updatedAt;
	
	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "heroes_abilities", 
        joinColumns = @JoinColumn(name = "ability_id"), 
        inverseJoinColumns = @JoinColumn(name = "hero_id")
    )
    private List<SuperAbility> abilities;
	
	@OneToMany(mappedBy="hero", fetch = FetchType.LAZY)
    private List<Nickname> nicknames;
	
	public Hero(String alias, String realName, String description) {
		this.alias = alias;
		this.realName = realName;
		this.description = description;
		this.createdAt = new Date();
		this.updatedAt = new Date();
	}
	
	public Hero() {
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
    public List<SuperAbility> getAbilities() {
		return abilities;
	}

	public void setAbilities(List<SuperAbility> abilities) {
		this.abilities = abilities;
	}

	public List<Nickname> getNicknames() {
		return nicknames;
	}

	public void setNicknames(List<Nickname> nicknames) {
		this.nicknames = nicknames;
	}

	@PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }
	
}
