package com.development.splitwise.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
@Getter
@Setter
@Entity(name = "Groups")
public class Group extends  BaseModel{

   private String groupName;
   @ManyToMany
    private List<User> user ; //(m:m relationship)
    private Date groupCreationDate;
    @ManyToOne
    private User groupCreatedBy;
    // List<Expense> expense; (ideally not add list until unless it needed, mostly m:m relationship we have to add list )
    private String  description;
    private Date createdBy;
}
