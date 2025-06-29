# Lab 5 (Activity2 ReadMe.md)

### Valid Queries for Tasks (1-5)
- 1. get list of Book(s) based on authorId  

query {
  booksByAuthorId(authorId: 1) {
    isbn
    title
    authorId
  }
}

- 2. get list of Author(s) based on lastName  

query {
  authorsByLastName(lastName: "Frost") {
    id
    firstName
    lastName
  }
}

- 3. update an Author's firstName based on Author's Id

mutation {
  updateAuthorFirstName(input: {
    authorId: 2
    newFirstName: "Bob"
  }) {
    oldFirstName
  }
}

- 4. delete a Book given it's ISBN

mutation {
  deleteBookByISBN(input: {
    isbn: "123456789"
  }) {
    isbn
  }
}

- 5. get list of all Book title(s) by Author(s) w/ given firstName

query {
  bookTitlesByAuthorFirstName(firstName: "Robert")
}