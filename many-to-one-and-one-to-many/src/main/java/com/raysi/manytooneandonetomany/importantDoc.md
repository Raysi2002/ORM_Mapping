# Advanced JPA/Hibernate Concepts Guide

## 1. Lazy vs Eager Loading

### Eager Loading
- Loads all associated data immediately when parent entity is loaded
- Can cause performance issues with large datasets
- Example:
```java
@OneToMany(fetch = FetchType.EAGER)
private List<Book> books;
```

Problems:
- Memory overload with large datasets
- Unnecessary data loading
- N+1 query problems in collections

Best Practice:
- Avoid EAGER loading by default
- Use only when you always need the related data and dataset is small

### Lazy Loading
- Loads associated data only when specifically requested
- Default for @OneToMany and @ManyToMany
- More efficient but requires careful handling
```java
@OneToMany(fetch = FetchType.LAZY)
private List<Book> books;
```

Common Issues:
1. LazyInitializationException
```java
@Service
public class LibraryService {
    public List<Book> getBooksWrong(Long libraryId) {
        Library library = libraryRepository.findById(libraryId).orElseThrow();
        // This will throw LazyInitializationException!
        return library.getBooks();  // Session is already closed
    }

    // Solution 1: Use JOIN FETCH in query
    @Transactional(readOnly = true)
    public List<Book> getBooksRight(Long libraryId) {
        return libraryRepository.findLibraryWithBooks(libraryId).getBooks();
    }
}

@Repository
public interface LibraryRepository extends JpaRepository<Library, Long> {
    @Query("SELECT l FROM Library l JOIN FETCH l.books WHERE l.id = :id")
    Library findLibraryWithBooks(@Param("id") Long id);
}
```

## 2. N+1 Query Problem

This occurs when you load a collection of entities and then access their lazy-loaded relationships, causing one additional query per entity.

Problem Example:
```java
List<Library> libraries = libraryRepository.findAll();
// This causes N+1 queries!
libraries.forEach(library -> {
    library.getBooks().size();  // One query per library!
});
```

Solutions:
1. Using JOIN FETCH:
```java
@Query("SELECT DISTINCT l FROM Library l JOIN FETCH l.books")
List<Library> findAllWithBooks();
```

2. Using EntityGraph:
```java
@EntityGraph(attributePaths = {"books"})
List<Library> findAll();
```

## 3. Cascade Types

Cascade types control how changes to parent entity affect child entities.

### Common Types:
1. CascadeType.ALL
    - Propagates all operations
    - Use carefully - might cause unintended deletions
```java
@OneToMany(cascade = CascadeType.ALL)
private List<Book> books;
```

2. CascadeType.PERSIST
    - Only propagates save operations
    - Good for creating parent-child together
```java
@OneToMany(cascade = CascadeType.PERSIST)
private List<Book> books;
```

3. CascadeType.MERGE
    - Only propagates updates
```java
@OneToMany(cascade = CascadeType.MERGE)
private List<Book> books;
```

4. CascadeType.REMOVE
    - Deletes children when parent is deleted
    - Use with caution!
```java
@OneToMany(cascade = CascadeType.REMOVE)
private List<Book> books;
```

Best Practices:
- Avoid CascadeType.ALL unless you're sure
- Use specific cascade types based on business logic
- Be especially careful with REMOVE cascade

## 4. Transient State

Transient annotations mark fields that shouldn't be persisted to database.

Use Cases:
1. Calculated fields
2. Temporary data
3. Helper properties

```java
@Entity
public class Book {
    @Transient
    private BigDecimal discountedPrice;  // Not saved to DB
    
    @PostLoad
    private void calculateDiscountedPrice() {
        this.discountedPrice = this.price.multiply(new BigDecimal("0.9"));
    }
}
```

## 5. Best Practices and Common Pitfalls

### 1. Circular References
Problem: Infinite recursion in JSON serialization

Solution:
```java
@JsonManagedReference  // On parent side
private List<Book> books;

@JsonBackReference     // On child side
private Library library;
```

### 2. Batch Operations
For large datasets:
```java
@Service
public class LibraryService {
    private static final int BATCH_SIZE = 100;
    
    @Transactional
    public void saveBooks(List<Book> books) {
        for (int i = 0; i < books.size(); i++) {
            if (i > 0 && i % BATCH_SIZE == 0) {
                entityManager.flush();
                entityManager.clear();
            }
            entityManager.persist(books.get(i));
        }
    }
}
```

### 3. Performance Tips
1. Use pagination for large datasets:
```java
Page<Library> findAll(Pageable pageable);
```

2. Avoid N+1 queries with proper fetching strategies
3. Use proper indexes on frequently queried columns
4. Consider using native queries for complex operations

### 4. Transaction Management
```java
@Service
public class LibraryService {
    @Transactional(readOnly = true)  // For read operations
    public Library getLibrary(Long id) {
        return libraryRepository.findById(id).orElseThrow();
    }
    
    @Transactional  // For write operations
    public Library updateLibrary(Library library) {
        // Propagates transaction to all called methods
        return libraryRepository.save(library);
    }
}
```