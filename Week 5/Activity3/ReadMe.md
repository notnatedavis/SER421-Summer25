# Lab 5 (personal ReadMe.md)

### Activity 1
- review and update file

### Activity 2
- using Insomnia make HTTP requests to the API endpoints
- test build & run example
- play around with available APIs
- copy folder out of git enabled repo into clean folder (twice, same starting code for A2 + A3)
- add all of the following : 
    - API to get list of `Books` based on `authorId`
    - API to get a list of `Author`(s) based on `lastName`
    - API to update `Author`('s) `firstName` based on `Author`('s) `authorId` , if successful returns old first name else return null
    - API to delete a `Book` given it's `ISBN` . if successful return `ISBN` deleted else return null
    - API to get a list of `Book` `title`(s) written by `Author`(s) with given `firstName`

### Activity 3
- with same starter code as A2...
- remove static initialization blocks in favor of a Spring JPA-enabled database
- must use the built-in H2 database (same as example for grocerydemojpa) (https://github.com/kgary/ser421public/tree/master/serverside/Spring/grocerydemojpa)