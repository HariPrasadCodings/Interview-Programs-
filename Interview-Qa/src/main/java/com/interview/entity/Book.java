package com.interview.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {

	private long bookId;
	private String title;
	private int publicationYear;
	private List<Author> authors;

}
