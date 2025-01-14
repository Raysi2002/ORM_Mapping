# ORM Mapping Documentation

## **Entity: Library**
The `Library` entity represents a library and maintains a one-to-many relationship with the `Book` entity.

### **Annotations**
- `@Entity`: Marks this class as a JPA entity.
- `@Id`: Specifies the primary key of the entity.
- `@GeneratedValue(strategy = GenerationType.AUTO)`: Automatically generates the primary key value.
- `@OneToMany`: Defines a one-to-many relationship between `Library` and `Book`.
    - `mappedBy = "library"`: Indicates that the `library` field in the `Book` entity owns the relationship.
    - `cascade = CascadeType.ALL`: Propagates all operations (e.g., persist, merge, remove) from `Library` to `Book`.
    - `orphanRemoval = true`: Automatically removes `Book` entities that are no longer referenced in the `books` list.
    - `fetch = FetchType.EAGER`: Ensures `Book` entities are loaded with `Library`.

### **Relationship Management Methods**
- `addBook(Book book)`: Adds a single book to the library and sets the library reference in the book.
- `addBooks(List<Book> books)`: Adds multiple books to the library.
- `removeBook(Book book)`: Removes a single book from the library and clears its library reference.
- `removeBooks(List<Book> books)`: Removes multiple books from the library.

### **toString()**
- Provides a custom string representation for debugging or logging purposes, including details about the library and its books.

---

## **Entity: Book**
The `Book` entity represents a book and maintains a many-to-one relationship with the `Library` entity.

### **Annotations**
- `@Entity`: Marks this class as a JPA entity.
- `@Id`: Specifies the primary key of the entity.
- `@GeneratedValue(strategy = GenerationType.AUTO)`: Automatically generates the primary key value.
- `@ManyToOne`: Defines a many-to-one relationship between `Book` and `Library`.
    - `@JoinColumn(name = "library_id", referencedColumnName = "libraryId")`: Specifies the foreign key column (`library_id`) in the `Book` table that references the `libraryId` column in the `Library` table.
    - `@JsonIgnore`: Prevents cyclic serialization issues during JSON serialization.

### **toString()**
- Provides a custom string representation for debugging or logging purposes, including details about the book.

---

## **Test Cases**
### **1. Save Data**
- **Purpose**: To test the saving of `Library` and `Book` entities in the database.
- **Description**:
    - Creates a `Book` entity with the author name and book name.
    - Creates a `Library` entity and associates the book(s) with it using the `addBooks` method.
    - Saves the `Library` entity, which cascades the save operation to associated `Book` entities.

### **2. Fetch Books**
- **Purpose**: To test fetching all libraries along with their associated books.
- **Description**:
    - Retrieves all libraries from the repository using `findAll()`.
    - Prints the libraries and their books.

### **3. Delete Book**
- **Purpose**: To test the deletion of a library and its associated books.
- **Description**:
    - Deletes a `Library` entity by its ID using `deleteById()`.
    - The `CascadeType.ALL` ensures that associated `Book` entities are also deleted.

---

## **Key Concepts Demonstrated**
1. **One-to-Many Relationship**:
    - Managed via `@OneToMany` in the `Library` entity and `@ManyToOne` in the `Book` entity.
    - The `mappedBy` attribute ensures bidirectional mapping with `Library` as the parent.

2. **Cascade Operations**:
    - Ensures that all CRUD operations on `Library` propagate to its associated `Book` entities.

3. **Orphan Removal**:
    - Automatically removes `Book` entities when they are removed from the `books` list in `Library`.

4. **Fetching Strategy**:
    - Uses `FetchType.EAGER` for immediate loading of associated `Book` entities.

5. **Relationship Management**:
    - The `addBook`, `addBooks`, `removeBook`, and `removeBooks` methods simplify maintaining the relationship integrity between `Library` and `Book`.

6. **Avoiding Cyclic References**:
    - The `@JsonIgnore` annotation prevents serialization issues during REST API calls.