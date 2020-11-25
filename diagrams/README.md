
E/R diagram

1. There is a duplication of orderID 
2. Each critic can only have one rating of a movie
3. Directors and producers are just stored as long strings

updated videotape to be weak on Movie*

note: add address, phone number for customer

alternative idea: Remove videotape as an entity, and just keep track of movies by adding a "stock" attribute for movies that we decrement and increment.

![video rental db](https://github.com/MasonDarcy/4312Project/blob/master/diagrams/videorental_ER_diagram.png (2)?raw=true "Title")
